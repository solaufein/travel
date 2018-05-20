package com.radek.travelplanet.service;

public interface TaskRepository {

    void save(int id, TaskInfo taskInfo);

    TaskInfo get(int id);
}
