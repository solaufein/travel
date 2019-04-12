package com.radek.travelplanet.service.task;

import com.radek.travelplanet.service.task.listener.OnFailureListener;
import com.radek.travelplanet.service.task.listener.OnSuccessListener;

public interface ListenableTask {
    void register(OnSuccessListener listener);
    void register(OnFailureListener listener);
}
