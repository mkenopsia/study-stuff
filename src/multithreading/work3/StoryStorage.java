package multithreading.work3;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class StoryStorage {

    private final Map<Long, StoryResult> storage = new ConcurrentHashMap<>();

    // upsert по taskId, с I/O задержкой
    public void save(StoryResult result) {
        storage.put(result.taskId(), result);
    }

    public Optional<StoryResult> get(long taskId) {
        return Optional.ofNullable(storage.get(taskId));
    }

    public int size() {
        return storage.size();
    }

    // немодифицируемая копия
    public Map<Long, StoryResult> snapshot() {
        return Collections.unmodifiableMap(storage);
    }

    public void clear() {
        storage.clear();
    }
}
