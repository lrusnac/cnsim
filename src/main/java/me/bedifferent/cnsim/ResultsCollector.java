package me.bedifferent.cnsim;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class ResultsCollector{
    private Map<String, List<Double>> result;

    public ResultsCollector(){
        this.result = new HashMap<String, List<Double>>();
    }

    public void aggregateData(Map<String, Double> data){
        for(String s : data.keySet()){
            List<Double> l = result.get(s);
            if(l == null){
                l = new ArrayList<Double>();
            }

            l.add(data.get(s));
            result.put(s, l);
        }
    }

    public String toString(){
        String r = "";
        for(String s : this.result.keySet()){
            r += (s + ": [");
            for(Double d : this.result.get(s)){
                r += (d + ", ");
            }
            r += "]\n";
        }
        return r;
    }
}
