package me.bedifferent.cnsim;

import java.util.Random;

public class Engine{
    private ResultsCollector rescol;

    public Engine(){
        rescol = new ResultsCollector();
    }

    public void run(int x, int s){
        System.out.println("Starting the simulation, it will make "+x+" experiments.");
        
        Random r = new Random(s);
        int seeds[] = new int[x];
        for(int i=0; i<x; seeds[i++] = r.nextInt());

        for(int i=0; i<x; i++){
            System.out.println("Experiment number: "+(i+1)+". Seed = "+s);
            
            rescol.aggregateData(this.makeExperiment(seeds[i]).popData());
        }

        System.out.println("Simulation fineshed with this results:");
        System.out.println(rescol);
    }

    private DataCollector makeExperiment(int seed){
        DataCollector dataCollector = new DataCollector();
        Network network = new Network();
        Random rand = new Random(seed);

        System.out.println("Network created");
        
        // setup the memory
        ResourceFactory rf = ResourceFactory.getInstance();
        //n.setMemory(rf.getResourcesFromFile("~/resources.txt"));
        network.setMemory(rf.getFakeResources());
        network.setDataCollector(dataCollector);
        
        Client c = network.getClient();
        Server server = network.getServer();
        Router source = server.getRouter();

        long time = 0;

        while(time < 10){ // later based on data from dataCollector
            //get a random resource
            Resource temp = server.getRandomResource(rand.nextInt(32000));
            //make a request
            c.requestResource(source, temp, time, rand);
            //register result to datacol
            //blah blah blah
            
            time = time + 1; // increment the simulation time
            //break;
        }

        return dataCollector;
    }
}
