package com.radek.travelplanet.service.task;

public interface TaskRepository {

    void save(TaskInfo taskInfo);

    TaskInfo get(Long taskId);

    void remove(Long taskId);
}
