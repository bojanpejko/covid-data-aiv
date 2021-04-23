package com.aiv.covid.vao;

import com.aiv.covid.observer.ObserverInterface;
import com.aiv.covid.observer.ObserverType;

public interface Subject {

    void attachObservers(ObserverInterface... observers);

    void notifyObservers(ObserverType type);

}
