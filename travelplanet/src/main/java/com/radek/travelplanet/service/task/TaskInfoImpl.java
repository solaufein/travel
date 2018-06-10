package com.radek.travelplanet.service.task;

import java.util.concurrent.Future;

class TaskInfoImpl implements TaskInfo {
    private final Long taskId;
    private final Future<?> future;

    public TaskInfoImpl(Long taskId, Future<?> future) {
        this.taskId = taskId;
        this.future = future;
    }

    @Override
    public Long getTaskId() {
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
