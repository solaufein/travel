package com.radek.travelplanet.service;

public interface TaskManager {

    void scheduleTask(Task task);

    void cancelTask(Task task);
}
