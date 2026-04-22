package multithreading.work3;

public record StoryTask(long id, String author, String title) {
    public static StoryTask of(long id, String author, String title) {
        if (id <= 0 || author == null || author.isBlank() || title == null ||
                title.isBlank()) {
            throw new IllegalArgumentException("Invalid task");
        }
        return new StoryTask(id, author, title);
    }
}
