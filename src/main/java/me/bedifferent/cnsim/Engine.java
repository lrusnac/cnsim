package me.bedifferent.cnsim;

public class Engine{
    private ResultsCollector rescol;
    private DataCollector datacol;
    private Network n;

    public Engine(){
        // create the network
        this.n = new Network();
        System.out.println("Network created");

        datacol = new DataCollector();
        rescol = new ResultsCollector();
    }

    public void run(int x){
        System.out.println("Starting the simulation, it will make "+x+" experiments.");
        
        for(int i=0; i<x; i++){
            int s = 1111; // TO-DO get a random seed for the experiment
            System.out.println("Experiment number: "+(i+1)+". Seed = "+s);
            datacol.flush(); // remove all previous data for a new experiment

            while(true){
                //make a request
                //register result to datacol
                //blah blah blah
                break;
            }
            
            rescol.aggregateData(datacol);
        }

        System.out.println("Simulation fineshed");
    }
}
