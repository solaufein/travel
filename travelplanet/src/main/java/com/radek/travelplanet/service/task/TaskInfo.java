package com.radek.travelplanet.service.task;

public interface TaskInfo {
    int getTaskId();
    boolean isCancelled();
    boolean isDone();
    void cancel();
}
