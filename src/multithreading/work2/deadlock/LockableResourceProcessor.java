package multithreading.work2.deadlock;

public interface LockableResourceProcessor {
    void processResources(LockableResource r1, LockableResource r2);
}
