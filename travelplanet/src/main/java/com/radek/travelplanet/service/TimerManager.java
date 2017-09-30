package com.radek.travelplanet.service;

import com.radek.travelplanet.model.Offer;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class TimerManager implements AutoCloseable {

    private final Timer timer;
    private final Map<String, TimerTask> timerTaskMap;

    private TimerManager() {
        this.timerTaskMap = new HashMap<String, TimerTask>();
        this.timer = new Timer();
    }

    public void addTimer(Offer offer) {
//        TimerTask timerTask = new PriceTimerTask(offer);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //todo: offer handling
            }
        };
        timerTaskMap.put(offer.getId(), timerTask);
        int frequency = Integer.parseInt(offer.getFrequency());

        timer.scheduleAtFixedRate(timerTask, 0, frequency * 1000);
    }

    public void removeTimer(Offer offer) {
        timerTaskMap.get(offer.getId()).cancel();
    }

    @Override
    public void close() {
        timer.cancel();
    }
}
