package com.radek.travelplanet.service;

import java.util.concurrent.TimeUnit;

public interface Task extends Runnable {
    long getFrequency();

    TimeUnit getTimeUnit();

    //todo: add methods: getStatus()
}
