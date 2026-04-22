package multithreading.work3;

import multithreading.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class StoryPipelineDemo {

    private static final int POOL_SIZE = 50;
    private static final int IO_MILLIS = 200;       // I/O-задержка на fetch/finalize/save
    private static final int CPU_MILLIS = 10;        // CPU нагрузка на edit
    private static final double CHANCE_OF_ERROR = 0.2; // вероятность ошибки на editText (0 < chance ≤ 1)
    private static final int TASKS_COUNT = 1_000; // вероятность ошибки на editText (0 < chance ≤ 1)

    private static final ExecutorService threadPool = Executors.newFixedThreadPool(POOL_SIZE);
    private static final ExecutorService virtualThreadPool = Executors.newVirtualThreadPerTaskExecutor();
    private static final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

    private static final StoryStorage storage = new StoryStorage();
    private static final StoryService service = new StoryService(IO_MILLIS, CPU_MILLIS, IO_MILLIS, CHANCE_OF_ERROR);

    public static void main(String[] args) {
        List<StoryTask> storyTasks = new ArrayList<>();

        for (int i = 0; i < TASKS_COUNT; i++) {
            var task = StoryTask.of(i + 1, "author", "title");
            storyTasks.add(task);
        }

        long nanos = System.nanoTime();
        runPipeline(storyTasks, threadPool);
        long elapsed = System.nanoTime() - nanos;
        Logger.log("Затрачено времени на обычном пуле потоков: %s мс", TimeUnit.MILLISECONDS.convert(elapsed, TimeUnit.NANOSECONDS));
        Logger.log("Размер хранилища %s", storage.size());

        storage.clear();
        storyTasks.clear();

        for (int i = 0; i < TASKS_COUNT; i++) {
            var task = StoryTask.of(i + 1, "author", "title");
            storyTasks.add(task);
        }

        nanos = System.nanoTime();
        runPipeline(storyTasks, virtualThreadPool);
        elapsed = System.nanoTime() - nanos;

        Logger.log("Затрачено времени на пуле виртуальных потоков: %s мс", TimeUnit.MILLISECONDS.convert(elapsed, TimeUnit.NANOSECONDS));
        Logger.log("Размер хранилища %s", storage.size());

        threadPool.close();
    }

    private static void runPipeline(List<StoryTask> storyTasks, ExecutorService executor) {
        CompletableFuture<?>[] futures = storyTasks.stream()
                .map(task ->
                        CompletableFuture.supplyAsync(() -> service.fetchDraft(task), executor)
                                .thenApplyAsync(service::editText, forkJoinPool)
                                .thenApplyAsync(editedText -> service.finalizeStory(task, editedText), executor)
                                .thenAccept(result -> {
                                    if (result != null) {
                                        storage.save(result);
                                    }
                                })
                                .exceptionally(ex -> {
//                                    Logger.log("Error processing task %d: %s: %s",
//                                            task.id(), ex.getClass().getSimpleName(), ex.getMessage());
                                    return null;
                                })
                )
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures).join();
    }
}
