package me.bedifferent.cnsim;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataCollector{
    private List<Collector> collectors;
    private Map<String, String> properties;

    public DataCollector(Map<String, String> properties) {
    	this.properties = properties;
    	
        this.collectors = new ArrayList<Collector>();

        String collectorsArray[] = this.properties.get("collectors").split(",");
		for(int i=0; i<collectorsArray.length; i++) {
			String colName = collectorsArray[i].split("#")[0];
			String colPar = collectorsArray[i].split("#")[1];
			
			Class<?> cl;
			Constructor<?> con;
			Collector collector;
			
			try {
				cl = Class.forName("me.bedifferent.cnsim." + colName);
				con = cl.getConstructor(String.class);
				collector = (Collector) con.newInstance(colPar);
			}catch(Exception e) {
				System.out.println("Errore specificazione nome collector");
				return;
			}
			
			this.collectors.add(collector);
		}
    }

    public void pushData(Event data){
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
