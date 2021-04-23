package com.aiv.covid.iterator;

import com.aiv.covid.vao.InfectedData;

public interface Iterator {

    boolean hasNext();
    InfectedData next();
}
