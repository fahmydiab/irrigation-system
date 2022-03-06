package com.example.irrigationsystem;

import com.example.irrigationsystem.model.PlotTimeSlot;
import com.example.irrigationsystem.model.PlotTimeSlotEnum;
import com.example.irrigationsystem.service.PlotTimeSlotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class TimeSlotScheduler {

    @Autowired
    private PlotTimeSlotService plotTimeSlotService;

    @Scheduled(cron = "${scheduler.cron}")
    public void startIrrigationSlot() {
        Set<PlotTimeSlot> plotTimeSlots = plotTimeSlotService.fetchAllAvailableInitSlots(LocalDateTime.now());
        if (!plotTimeSlots.isEmpty()) {
            irrigate(plotTimeSlots.iterator().next());
        }
        plotTimeSlots.stream().filter(p->p.getStatus()==PlotTimeSlotEnum.INIT).forEach(p->p.setStatus(PlotTimeSlotEnum.FAILED));
    }

    @Retryable(value = {Exception.class}, maxAttemptsExpression = "${retry.maxAttempts}", backoff = @Backoff(delayExpression = "${retry.delay}"))
    private void irrigate(PlotTimeSlot slot) {
                try {
                    slot.getPlot().irrigate(slot);
                } catch (Exception e) {
                    e.printStackTrace();
                }
    }

}
