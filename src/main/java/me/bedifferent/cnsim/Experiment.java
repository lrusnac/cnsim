package me.bedifferent.cnsim;

import java.util.Random;

public class Experiment extends Thread{
    private int seed = 0;
    private ResultsCollector rescol;

    public Experiment(int seed, ResultsCollector rescol){
        this.seed = seed;
        this.rescol = rescol;
    }

    public void run(){
        DataCollector dataCollector = new DataCollector();
        Network network = new Network();
        //Random rand = new Random(seed);
        RngStream rand = new RngStream();
        rand.setSeed(new long[]{seed, 12345, 12345, 12345, 12345, 12345});

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

        while(time < 1000000){ // later based on data from dataCollector
            //get a random resource
        	Resource temp = server.getRandomResource(rand.randInt(0, 32000));
           // Resource temp = server.getRandomResource(rand.nextInt(32000));
            //make a request
            c.requestResource(source, temp, time, rand);
            //register result to datacol
            //blah blah blah
            
            time = time + 1; // increment the simulation time
            //break;
        }
        
        this.rescol.aggregateData(dataCollector.popData());
    }
}
