package com.radek.travelplanet.service.task;

import java.util.concurrent.Future;

class TaskInfoImpl implements TaskInfo {
    private final int taskId;
    private final Future<?> future;

    public TaskInfoImpl(int taskId, Future<?> future) {
        this.taskId = taskId;
        this.future = future;
    }

    @Override
    public int getTaskId() {
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
