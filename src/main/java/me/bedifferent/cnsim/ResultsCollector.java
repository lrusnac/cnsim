package me.bedifferent.cnsim;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultsCollector{
    private Map<String, List<Double>> result;
    private Map<String, Double> finalResult;
    private DecimalFormat df = new DecimalFormat("##0.000");


    public ResultsCollector(){
        this.result = new HashMap<String, List<Double>>();
        this.finalResult = new HashMap<String, Double>();
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

    public void getResoults() {
        for(String s : result.keySet()){
            List<Double> l = result.get(s);
            double sum = 0.0;
            for(Double val : l)
                sum += val;
            this.finalResult.put(s, sum/l.size());
        }
    }

    /*public String toString(){
        String r = "";
        for(String s : this.result.keySet()){
            r += (s + ": [");
            for(Double d : this.result.get(s)){
                r += (d + ", ");
            }
            r += "]\n";
        }
        return r;
    }*/

    public String toString() {
        getResoults();
        String r = "";
        for(String s : this.finalResult.keySet()){
            r += (s + ":\t");
            r += df.format(this.finalResult.get(s));
            r += "\n";
        }
        return r;
    }
}
