package me.bedifferent.cnsim;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class DataCollector{
    private List<Collector> collectors;

    public DataCollector(){
        this.collectors = new ArrayList<Collector>();
    }

    public void pushData(){ // TO-DO
        for(Collector c : this.collectors)
            c.onEvent();
    }

    public Map<String, String> popData(){
        return null;
    }

    public void registerCollector(Collector c){
        this.collectors.add(c);
    }

    public void flush(){
    
    }
}
