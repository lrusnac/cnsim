package me.bedifferent.cnsim;

import java.util.Map;
import java.util.Random;

public class Engine{
    private ResultsCollector rescol;
    private Map<String, String> properties;
    
    public Engine(Map<String, String> properties){
        rescol = new ResultsCollector(properties);
        this.properties = properties;
    }

    public void run(int x, int s){
        System.out.println("Starting the simulation, it will make "+x+" experiments.");
        
        Random r = new Random(s);
        int seeds[] = new int[x];
        for(int i=0; i<x; seeds[i++] = r.nextInt(10000));

        Thread threads[] = new Thread[x];
        try{
            for(int i=0; i<x; i++){
                System.out.println("Experiment number: "+(i+1)+". Seed = "+seeds[i]);
                threads[i] = new Experiment(seeds[i], this.rescol, this.properties);
                threads[i].start();
            }

            for(int i=0; i<x; i++)
                threads[i].join();
        }catch(Exception e){
            System.out.println("Exception");
        }
        System.out.println("Simulation fineshed with this results:");
        System.out.println(rescol);
    }

}
