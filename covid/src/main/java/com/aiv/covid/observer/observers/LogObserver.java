package com.aiv.covid.observer.observers;

import com.aiv.covid.observer.ObserverInterface;
import com.aiv.covid.vao.InfectedData;
import com.aiv.covid.vao.Region;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@Data
public class LogObserver implements ObserverInterface {

    @Valid
    @NotNull(message = "{covid.observer.LogObserver.missing.subject}")
    private Region subject;

    @Override
    public void update() {
        int position = subject.getCalendar().size() - 1;
        InfectedData newAddition = subject.getCalendar().get(position);

        log.info(subject.toString());
        log.info(newAddition.toString());
    }
}
