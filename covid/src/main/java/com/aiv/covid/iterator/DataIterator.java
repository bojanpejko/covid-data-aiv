package com.aiv.covid.iterator;

import com.aiv.covid.vao.InfectedData;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class DataIterator implements Iterator{

    private List<InfectedData> data;

    private int index;

    @Override
    public boolean hasNext() { return index < data.size(); }

    @Override
    public InfectedData next() {
        if(this.hasNext()){
            return data.get(index++);
        }
        return null;
    }
}
