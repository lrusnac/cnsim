package me.bedifferent.cnsim;

import java.util.Random;

public class Engine{
    private ResultsCollector rescol;
    private DataCollector datacol;
    private Network n;

    public Engine(){
        // create the network
        this.n = new Network();
        System.out.println("Network created");
        
        // setup the memory
        ResourceFactory rf = ResourceFactory.getInstance();
        //n.setMemory(rf.getResourcesFromFile("~/resources.txt"));
        n.setMemory(rf.getFakeResources());
        datacol = new DataCollector();
        rescol = new ResultsCollector();
    }

    public void run(int x, int s){
        System.out.println("Starting the simulation, it will make "+x+" experiments.");
        
        n.setDataCollector(datacol);

        for(int i=0; i<x; i++){
            System.out.println("Experiment number: "+(i+1)+". Seed = "+s);
            datacol.flush(); // remove all previous data for a new experiment
            n.clearCache(); // clear the cache of the routers
            
            Random rand = new Random(s);
            int time = 0; // reset the time of every experiment

            // I'm doing this for speed up the loop, but you can make
            // requests on various clients and servers too.
            Client c = n.getClient();
            Server server = n.getServer();
            Router source = server.getRouter();

            while(time < 10){
                //get a random resource
                Resource temp = server.getRandomResource(rand.nextInt(32000));
                //make a request
                c.requestResource(source, temp, time, rand);
                //register result to datacol
                //blah blah blah
                
                time = time + 1; // increment the simulation time
                //break;
            }
            
            rescol.aggregateData(datacol.popData());

            s = rand.nextInt(32000);
        }

        System.out.println("Simulation fineshed with this results:");
        System.out.println(rescol);
    }
}
