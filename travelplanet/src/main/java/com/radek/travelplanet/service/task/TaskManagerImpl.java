package com.radek.travelplanet.service.task;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskManagerImpl implements TaskManager, AutoCloseable {

    private final TaskRepository taskRepository;
    private final TaskScheduler taskScheduler;

    public TaskManagerImpl(TaskRepository taskRepository, TaskScheduler taskScheduler) {
        this.taskRepository = taskRepository;
        this.taskScheduler = taskScheduler;
    }

    @Override
    public void startTask(Task task) {
        TaskInfo taskInfo = taskScheduler.execute(task);
        taskRepository.save(taskInfo);
    }

    @Override
    public void cancelTask(Long taskId) {
        TaskInfo taskInfo = taskRepository.get(taskId);
        if (taskInfo != null) {
            if (!taskInfo.isCancelled()) {
                taskInfo.cancel();
            }
        } else {
            log.warn("Not found TaskInfo for taskId: {}", taskId);
        }
    }

    @Override
    public void removeTask(Long taskId) {
        taskRepository.remove(taskId);
    }

    @Override
    public void close() {
        taskScheduler.close();
    }
}
