package com.radek.travelplanet.service.task;

public interface TaskManager {

    void startTask(Task task);

    void cancelTask(Long taskId);

    void removeTask(Long taskId);
}
