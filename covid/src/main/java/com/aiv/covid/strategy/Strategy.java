package com.aiv.covid.strategy;

import com.aiv.covid.vao.Region;

public interface Strategy {

    String adaptMessage(Region region);
}
