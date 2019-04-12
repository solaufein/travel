package com.radek.travelplanet.service.task;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class InMemoryTaskRepository implements TaskRepository {

    private final Map<Long, TaskInfo> tasks;

    public InMemoryTaskRepository() {
        this(new ConcurrentHashMap<>());
    }

    public InMemoryTaskRepository(Map<Long, TaskInfo> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void save(TaskInfo taskInfo) {
        tasks.put(taskInfo.getTaskId(), taskInfo);
    }

    @Override
    public TaskInfo get(Long taskId) {
        return tasks.get(taskId);
    }

    @Override
    public void remove(Long taskId) {
        if (tasks.containsKey(taskId)) {
            TaskInfo taskInfo = tasks.get(taskId);
            if (taskInfo.isCancelled() || taskInfo.isDone()) {
                tasks.remove(taskId);
            } else {
                log.warn("Cannot remove task: {}, because it is in progress.", taskId);
            }
        } else {
            log.warn("Cannot remove task: {}, because it not exists.", taskId);
        }
    }
}
