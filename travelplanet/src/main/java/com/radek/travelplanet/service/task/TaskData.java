package com.radek.travelplanet.service.task;

import lombok.Value;

@Value
public class TaskData {
    private final Long id;
    private final String link;
    private final String frequency;
    private final long initialDelay;
    private final String name;
}
