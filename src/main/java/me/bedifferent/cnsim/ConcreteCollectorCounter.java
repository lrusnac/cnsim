package me.bedifferent.cnsim;

import java.util.HashMap;
import java.util.Map;

public class ConcreteCollectorCounter extends Collector{
    private Map<String, Double> collector;
    private Event.Type type;

    public ConcreteCollectorCounter(Event.Type type){
        this.collector = new HashMap<String, Double>();
        this.type = type;
    }

    public Map<String, Double> getValue(){
        return this.collector;
    }

    public void onEvent(Event data){
        Double l = this.collector.get(data.getDescription()+this.type);
        if(l == null){
            l = new Double(0.0);
        }

        l += data.getType() == this.type ? 1 : 0;
        this.collector.put(data.getDescription()+this.type, l);
    }

    public void clear(){
        this.collector = new HashMap<String, Double>();
    }
}
