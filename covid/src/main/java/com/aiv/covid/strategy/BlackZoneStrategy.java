package com.aiv.covid.strategy;

import com.aiv.covid.vao.Region;

public class BlackZoneStrategy implements Strategy{

    @Override
    public String adaptMessage(Region region) {
        return "Regija: " + region.toString() + "\nTrenutno je v črni fazi. ";
    }
}
