package com.radek.travelplanet.service.task;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskManagerImpl implements TaskManager, AutoCloseable {

    private final TaskRepository taskRepository;
    private final TaskRunner taskRunner;

    public TaskManagerImpl(TaskRepository taskRepository, TaskRunner taskRunner) {
        this.taskRepository = taskRepository;
        this.taskRunner = taskRunner;
    }

    @Override
    public void startTask(Task task) {
        TaskInfo taskInfo = taskRunner.execute(task);
        taskRepository.save(taskInfo);
    }

    @Override
    public void cancelTask(String taskId) {
        TaskInfo taskInfo = taskRepository.get(taskId);
        if (taskInfo != null) {
            taskInfo.cancel();
        } else {
            log.warn("Not found TaskInfo for taskId: {}", taskId);
        }
    }

    @Override
    public void close() {
        taskRunner.close();
    }
}
