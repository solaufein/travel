package com.radek.travelplanet.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryTaskRepository implements TaskRepository {

    private final Map<Integer, TaskInfo> tasks;

    public InMemoryTaskRepository() {
        this(new ConcurrentHashMap<>());
    }

    public InMemoryTaskRepository(Map<Integer, TaskInfo> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void save(int id, TaskInfo taskInfo) {
        tasks.put(id, taskInfo);
    }

    @Override
    public TaskInfo get(int id) {
        return tasks.get(id);
    }
}
