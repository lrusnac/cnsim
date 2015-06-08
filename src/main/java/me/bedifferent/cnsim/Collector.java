package me.bedifferent.cnsim;

import java.util.Map;

public interface Collector{
    public Map<String, Double> getValue();

    public void onEvent(Event data);
    public void clear(); 
}
