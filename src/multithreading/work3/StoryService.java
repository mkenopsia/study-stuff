package multithreading.work3;

import multithreading.ThreadUtil;

import java.util.concurrent.ThreadLocalRandom;

public final class StoryService {

    private final int fetchDraftMs;
    private final int editTextMs;
    private final int finalizeMs;
    private final double chanceOfEditingError;

    // все ms ≥ 0; 0 < chanceOfEditingError ≤ 1 → иначе IllegalArgumentException
    public StoryService(int fetchDraftMs, int editTextMs, int finalizeMs, double chanceOfEditingError) {
        if (fetchDraftMs < 0 || editTextMs < 0 || finalizeMs < 0 || chanceOfEditingError < 0 || chanceOfEditingError > 1) {
            throw new IllegalArgumentException();
        }

        this.fetchDraftMs = fetchDraftMs;
        this.editTextMs = editTextMs;
        this.finalizeMs = finalizeMs;
        this.chanceOfEditingError = chanceOfEditingError;
    }

    // Имитация внешнего I/O (блокирующая задержка): вернёт "draft"
    public String fetchDraft(StoryTask task) {
        ThreadUtil.sleep(fetchDraftMs);
        return "Draft: " + task;
    }

    // Имитация CPU (busyloop); с заданной вероятностью бросает исключение
    public String editText(String draft) {
        long millis = System.currentTimeMillis();
        while (System.currentTimeMillis() - millis < editTextMs) {
            long ignored = 113*12*7;
        }
        var chance = ThreadLocalRandom.current().nextDouble();
        if (chance < chanceOfEditingError) {
            throw new RuntimeException("Ошибка по вероятности");
        }
        return "Edited: " + draft;
    }

    // Имитация I/O + формирование StoryResult
    public StoryResult finalizeStory(StoryTask task, String editedText) {
        ThreadUtil.sleep(finalizeMs);
        return StoryResult.of(task.id(), editedText);
    }
}
