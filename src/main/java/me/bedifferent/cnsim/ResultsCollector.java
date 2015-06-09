package me.bedifferent.cnsim;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultsCollector{
    private Map<String, List<Double>> result;
    private Map<String, Interval> finalResult;
    private Map<String, String> properties;
    private Double confindenceIntervalLevel;
    private DecimalFormat df = new DecimalFormat("##0.000");

    public ResultsCollector(Map<String, String> properties){
    	this.properties = properties;
        this.result = new HashMap<String, List<Double>>();
        this.finalResult = new HashMap<String, Interval>();
        this.confindenceIntervalLevel = Double.valueOf(properties.get("confidence_interval_level"));
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
            double sumQ = 0.0;
            for(Double val : l){
                sum += val;
                sumQ += val*val;
            }
            
            Interval temp = new Interval(sum/l.size(), sumQ / l.size() - Math.pow(sum/l.size(), 2), l.size());
            temp.evaluateConfidenceInterval(confindenceIntervalLevel);
            this.finalResult.put(s, temp);
            
        }
    }

    public String toString() {
        getResoults();
        String r = "";
        for(String s : this.finalResult.keySet()){
            r += (s + ":\n\t");
            r += "confidence interval:\t[" + df.format(this.finalResult.get(s).getConfIntL()) + ", " +df.format(this.finalResult.get(s).getConfIntR()) + "]";
            r += "\n\tmean:\t\t\t" + df.format(this.finalResult.get(s).getMean());
            r += "\n";
        }
        return r;
    }
    
    private class Interval{
    	private double mean;
    	private double variance;
    	private double confIntL;
    	private double confIntR;
    	private int num;
    	
    	public Interval(double mean, double variance, int num) {
    		this.mean = mean;
    		this.variance = variance;
    		this.num = num;
    	}

		public double getMean() {
			return mean;
		}

		public void setMean(double mean) {
			this.mean = mean;
		}

		public double getVariance() {
			return variance;
		}

		public void setVariance(double variance) {
			this.variance = variance;
		}

		public double getConfIntL() {
			return confIntL;
		}

		public void setConfIntL(double confIntL) {
			this.confIntL = confIntL;
		}

		public double getConfIntR() {
			return confIntR;
		}

		public void setConfIntR(double confIntR) {
			this.confIntR = confIntR;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}
    	
		public void evaluateConfidenceInterval(double level) {
			double temp = this.variance/Math.sqrt(num);
			double value = 0;
			
			switch((int)(level*100)) {
			case 95:
				value = 1.96;
				break;
			case 98:
				value = 2.33;
				break;
			}

			
			this.confIntL = mean - value * temp;
			this.confIntR = mean + value * temp;
		}
    }
}
