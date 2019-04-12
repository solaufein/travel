package com.radek.travelplanet.service.task.listener;

import com.radek.travelplanet.service.task.Task;

public interface OnFailureListener {
    void onFailure(Task task, Exception ex);
}
