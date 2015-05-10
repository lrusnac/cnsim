package me.bedifferent.cnsim;

public class Zipfian {
	private double[] distributionMap;
	private double alpha;

	public Zipfian(double alpha, int n){
		this.distributionMap = new double[n+1];
		this.alpha = alpha;
		this.init(n);
	}

	private void init(int n){
		double[] tmp = new double[n];
		for(int i=1; i<n+1; i++){
			tmp[i-1] = 1.0 / Math.pow(i, this.alpha);
		}

		double[] zeta = new double[n+1];
		zeta[0] = 0;
		for(int i=1; i<n+1; i++){
			zeta[i] = zeta[i-1] + tmp[i-1];
		}

		for(int i=0; i<n+1; i++){
			distributionMap[i] = zeta[i] / zeta[n];
		}
	}

	public int next(double val){
		return this.next(val, 0, 1000) - 1;
	}

	private int next(double val, int min, int max){
		if(min > max){
			return -1;
		}

		if(min == max-1) {
			return max;
		}

		int middle = (min + max) / 2;
		if(val == distributionMap[middle]){
			return middle;
		}

		if(val < distributionMap[middle]){
			return this.next(val, min, middle);
		}

		return this.next(val, middle, max);
	}
}
