package com.radek.travelplanet.service.task;

import java.util.concurrent.Future;

class TaskInfoImpl implements TaskInfo {

    private final Task task;
    private final Future<?> future;

    public TaskInfoImpl(Task task, Future<?> future) {
        this.task = task;
        this.future = future;
    }

    @Override
    public Long getTaskId() {
        return task.getId();
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

    @Override
    public TaskStatus getTaskStatus() {
        return task.getStatus();
    }
}
