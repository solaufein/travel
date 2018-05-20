package com.radek.travelplanet.service;

public interface TaskInfo {
    boolean isCancelled();
    boolean isDone();
    void cancel();
}
