package com.radek.travelplanet.service.task;

public interface TaskInfo {
    Long getTaskId();
    boolean isCancelled();
    boolean isDone();
    void cancel();
}
