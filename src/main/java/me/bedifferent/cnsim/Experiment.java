package me.bedifferent.cnsim;

import java.util.Map;

public class Experiment extends Thread{
    private int seed = 0;
    private ResultsCollector rescol;
    private Map<String, String> properties;

    public Experiment(int seed, ResultsCollector rescol, Map<String, String> properties){
        this.seed = seed;
        this.rescol = rescol;
        this.properties = properties;
    }

    public void run(){
        DataCollector dataCollector = new DataCollector(this.properties);
        Network network = new Network(this.properties);

        RngStream rand = new RngStream();
        rand.setSeed(new long[]{seed, 12345, 12345, 12345, 12345, 12345});

        System.out.println("Network created");
        
        // setup the memory
        ResourceFactory rf = ResourceFactory.getInstance();
        if(this.properties.get("resource_file").equals("dummy")) {
        	int number = Integer.parseInt(this.properties.get("resource_number"));
        	int dimension = Integer.parseInt(this.properties.get("resource_dimension"));
        	network.setMemory(rf.getFakeResources(number, dimension));
        } else {
        	network.setMemory(rf.getResourcesFromFile(this.properties.get("resource_file")));
        }
        
        network.setDataCollector(dataCollector);
        
        Client c = network.getClient();
        Server server = network.getServer();
        Router source = server.getRouter();

        Zipfian zipfian = new Zipfian(0.85, 1000);
        
        long time = 0;

        while(time < 1000000){ // later based on data from dataCollector
            //get a random resource
            Resource temp = server.getRandomResource(zipfian.next(rand.randU01()));
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
