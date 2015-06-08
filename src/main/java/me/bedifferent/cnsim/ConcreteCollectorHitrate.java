package me.bedifferent.cnsim;

import sun.security.krb5.internal.crypto.Des;

import javax.swing.event.HyperlinkEvent;
import java.util.HashMap;
import java.util.Map;

public class ConcreteCollectorHitrate implements Collector{
    private Map<String, Hitrate> collector;
    private String resourceName;

    public ConcreteCollectorHitrate(String resourceName){
        this.collector = new HashMap<String, Hitrate>();
        this.resourceName = resourceName;
    }

    public Map<String, Double> getValue(){
        HashMap<String, Double> res = new HashMap<String, Double>();
        for(String str : this.collector.keySet()) {
            res.put(str, this.collector.get(str).getHitRate());
        }
        return res;
    }

    public void onEvent(Event data){
        if(this.resourceName.equals(data.getResource().getName())) {
            Hitrate hr = this.collector.get(data.getDescription() + " " + this.resourceName);
            if (hr == null) {
                hr = new Hitrate();
            }

            hr.registerEvent(data.getType());
            this.collector.put(data.getDescription() + " " + this.resourceName, hr);
        }
    }

    public void clear(){
        this.collector = new HashMap<String, Hitrate>();
    }

    private class Hitrate {
        private int missCount;
        private int hitCount;

        public Hitrate() {
            this.missCount = 0;
            this.hitCount = 0;
        }

        public void registerEvent(Event.Type evType) {
            if (evType == Event.Type.HIT) {
                this.hitCount ++;
            } else {
                this.missCount ++;
            }
        }

        public Double getHitRate() {
            return (1.0 * hitCount) / (missCount + hitCount);
        }
    }
}
