package me.bedifferent.cnsim;

import java.util.List;

public class ResourceFactory{
    //This class generates the resources, simply because there are
    //several ways to create and save the resources, for instance from
    //file or from a database, or even random generated resources
    
    private ResourceFactory instance;
    
    private ResourceFactory(){
        //it is a singleton class so has a private costructor, I need
        //only one instance of this class
    }

    public synchronized ResourceFactory getInstance(){
        // sychronized for multithreading porposes, in this way I am
        // sure to have only one instance of this class
        if(this.instance == null)
            this.instance = new ResourceFactory();
        return this.instance;
    }

    public List<Resource> getResourcesFromFile(String filepath){
        return null;
    }
}
