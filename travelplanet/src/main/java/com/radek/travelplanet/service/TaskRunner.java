package com.radek.travelplanet.service;

public interface TaskRunner {
    void close();

    TaskInfo execute(Task task);
}
