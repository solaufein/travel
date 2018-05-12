package com.radek.travelplanet.service;

import java.util.concurrent.TimeUnit;

public interface Task extends Runnable {

    int getId();

    long getFrequency();

    TimeUnit getTimeUnit();

    TaskStatus getStatus();
}
