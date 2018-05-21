package com.radek.travelplanet.service.task;

import java.util.concurrent.Future;

class TaskInfoImpl implements TaskInfo {
    private final String taskId;
    private final Future<?> future;

    public TaskInfoImpl(String taskId, Future<?> future) {
        this.taskId = taskId;
        this.future = future;
    }

    @Override
    public String getTaskId() {
        return taskId;
    }

    @Override
    public boolean isCancelled() {
        return future.isCancelled();
    }

    @Override
    public boolean isDone() {
        return future.isDone();
    }

    @Override
    public void cancel() {
        future.cancel(false);
    }
}
