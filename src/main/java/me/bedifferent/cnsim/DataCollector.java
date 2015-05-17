package me.bedifferent.cnsim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataCollector{
    private List<Collector> collectors;

    public DataCollector(){
        this.collectors = new ArrayList<Collector>();
        //this.collectors.add(new ConcreteCollectorCounter(Event.Type.HIT));
        //this.collectors.add(new ConcreteCollectorCounter(Event.Type.MISS));

        this.collectors.add(new ConcreteCollectorHitrate("Res0"));
        this.collectors.add(new ConcreteCollectorHitrate("Res9"));
        this.collectors.add(new ConcreteCollectorHitrate("Res99"));
        this.collectors.add(new ConcreteCollectorHitrate("Res999"));
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
