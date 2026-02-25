package multithreading.work2.deadlock;

import java.util.concurrent.locks.ReentrantLock;

public final class LockableResource {
    private final String name;
    private final int id;
    private final ReentrantLock lock = new ReentrantLock();
    private final Object monitor = new Object();

    public LockableResource(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Object getMonitor() {
        return monitor;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    @Override
    public String toString() {
        return "Resource-" + name;
    }
}
