package me.bedifferent.cnsim;

import java.util.List;
import java.util.ArrayList;

public class ResourceFactory{
    //This class generates the resources, simply because there are
    //several ways to create and save the resources, for instance from
    //file or from a database, or even random generated resources
    
        private static ResourceFactory instance;
    
    private ResourceFactory(){
        //it is a singleton class so has a private costructor, I need
        //only one instance of this class
    }

    public static synchronized ResourceFactory getInstance(){
        // sychronized for multithreading porposes, in this way I am
        // sure to have only one instance of this class
        if(instance == null)
            instance = new ResourceFactory();
        return instance;
    }

    public List<Resource> getResourcesFromFile(String filepath){
        List<Resource> result = new ArrayList<Resource>();
        result.add(new Resource("Prova", 1024));
        result.add(new Resource("Prova2", 2048));
        
        return result;
    }
}
