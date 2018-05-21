package com.radek.travelplanet.service.task;

public interface TaskRepository {

    void save(TaskInfo taskInfo);

    TaskInfo get(int id);
}
