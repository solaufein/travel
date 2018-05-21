package com.radek.travelplanet.service.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskManagerImpl implements TaskManager, AutoCloseable {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskManagerImpl.class);
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
            LOGGER.warn("Not found TaskInfo for taskId: {}", taskId);
        }
    }

    @Override
    public void close() {
        taskRunner.close();
    }
}
