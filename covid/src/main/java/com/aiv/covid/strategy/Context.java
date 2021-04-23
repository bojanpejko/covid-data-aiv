package com.aiv.covid.strategy;

import com.aiv.covid.vao.Region;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Context {
    private Strategy strategy;

    public String executeStrategy(Region region){ return strategy.adaptMessage(region); }

}
