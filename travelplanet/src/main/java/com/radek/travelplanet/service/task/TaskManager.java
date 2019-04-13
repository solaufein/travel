package com.radek.travelplanet.service.task;

import java.util.Optional;

public interface TaskManager {

    void startTask(Task task);

    void cancelTask(Long taskId);

    void removeTask(Long taskId);

    Optional<TaskStatus> getTaskStatus(Long taskId);
}
