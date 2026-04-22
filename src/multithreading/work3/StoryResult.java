package multithreading.work3;

public record StoryResult(long taskId, String finalText) {
    public static StoryResult of(long taskId, String text) {
        if (taskId <= 0 || text == null || text.isBlank()) {
            throw new IllegalArgumentException("Invalid result");
        }
        return new StoryResult(taskId, text);
    }
}
