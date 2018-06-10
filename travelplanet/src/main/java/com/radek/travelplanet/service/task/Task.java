package com.radek.travelplanet.service.task;

import java.util.concurrent.TimeUnit;

public interface Task extends Runnable {

    Long getId();

    long getFrequency();

    long getInitialDelay();

    TimeUnit getTimeUnit();

    TaskStatus getStatus();

    void updateStatus(TaskStatus taskStatus);
}
