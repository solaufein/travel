package com.radek.travelplanet.service.task;

@FunctionalInterface
public interface TaskCommand {
    void execute();
}
