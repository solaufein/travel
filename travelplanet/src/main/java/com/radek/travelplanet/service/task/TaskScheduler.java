package com.radek.travelplanet.service.task;

public interface TaskScheduler {
    void close();

    TaskInfo execute(Task task);
}
