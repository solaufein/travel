package com.radek.travelplanet.service.task;

public interface TaskInfo {
    String getTaskId();
    boolean isCancelled();
    boolean isDone();
    void cancel();
}
