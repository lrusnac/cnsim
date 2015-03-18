package me.bedifferent.cnsim;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class DataCollector{
    private List<Collector> collectors;

    public DataCollector(){
        this.collectors = new ArrayList<Collector>();
    }

    public void pushData(Event data){ // TO-DO
        for(Collector c : this.collectors)
            c.onEvent(data);
    }

    public Map<String, Double> popData(){
        Map<String, Double> result = new HashMap<String, Double>();
        for(Collector c : this.collectors)
            result.putAll(c.getValue());
        return result;
    }

    public void registerCollector(Collector c){
        this.collectors.add(c);
    }

    public void flush(){
        for(Collector c : this.collectors)
            c.clear();
    }
}
