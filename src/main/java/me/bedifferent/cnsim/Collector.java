package me.bedifferent.cnsim;

import java.util.Map;

public abstract class Collector{
    public abstract Map<String, Double> getValue();

    public abstract void onEvent(Event data);
    public abstract void clear(); 
}
