package com.radek.travelplanet.service.task;

public interface TaskRunner {
    void close();

    TaskInfo execute(Task task);
}
