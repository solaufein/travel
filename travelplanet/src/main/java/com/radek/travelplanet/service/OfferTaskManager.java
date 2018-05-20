package com.radek.travelplanet.service;

public class OfferTaskManager implements TaskManager, AutoCloseable {

    private final TaskRepository taskRepository;
    private final TaskRunner taskRunner;

    public OfferTaskManager(TaskRepository taskRepository, TaskRunner taskRunner) {
        this.taskRepository = taskRepository;
        this.taskRunner = taskRunner;
    }

    @Override
    public void startTask(Task task) {
        TaskInfo taskInfo = taskRunner.execute(task);
        taskRepository.save(task.getId(), taskInfo);
    }

    @Override
    public void cancelTask(Task task) {
        TaskInfo taskInfo = taskRepository.get(task.getId());
        taskInfo.cancel();
    }

    @Override
    public void close() {
        taskRunner.close();
    }
}
