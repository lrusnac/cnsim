package me.bedifferent.cnsim;

import java.util.Map;

public abstract class Collector{
    private String name;

    public Collector(String name){
        this.name = name;
    }

    public abstract Map<String, Double> getValue();

    public abstract void onEvent();

    public String getName(){
        return this.name;
    }
}
