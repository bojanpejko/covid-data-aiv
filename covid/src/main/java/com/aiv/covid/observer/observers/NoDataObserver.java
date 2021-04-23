package com.aiv.covid.observer.observers;

import com.aiv.covid.observer.ObserverInterface;
import com.aiv.covid.vao.Region;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@Data
public class NoDataObserver implements ObserverInterface {

    @Valid
    @NotNull(message = "{covid.observer.DeleteLogObserver.missing.subject}")
    private Region subject;

    @Override
    public void update() {
        if(subject.getCalendar().isEmpty()) {
            log.info("Region: " + subject.getName() + " has no data");
        }
    }
}
