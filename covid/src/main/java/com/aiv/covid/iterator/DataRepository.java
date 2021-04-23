package com.aiv.covid.iterator;

import com.aiv.covid.vao.InfectedData;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class DataRepository implements Container {

    private List<InfectedData> data;

    @Override
    public Iterator getIterator() {
        return new DataIterator(data, 0);
    }
}
