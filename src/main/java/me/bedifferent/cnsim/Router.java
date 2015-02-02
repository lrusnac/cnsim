package me.bedifferent.cnsim;

import java.util.List;
import java.util.Map;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Random;

public class Router{
    private List<Router> neighbours;
    private int cacheDim; //in Mbyte
    private Map<Resource, Description> cache;
    private String name;

    public Router(String name){
        this.name = name;
        this.neighbours = new ArrayList<Router>();
        this.cache = new HashMap<Resource, Description>();
    }

    public void addNeighbour(Router r){
        this.neighbours.add(r);
    }

    public void requestResource(Router source, Resource res, long time){
        //get a resource from a net
        if(this.cache.get(res).useResource(time)){
            System.out.println(this + " Resource present");
        }else{
            // if I have the source router as neighbour then take the
            // resource from him directlly
            if(this.neighbours.contains(source)){
                source.requestResource(source, res, time);
            } 
        }
    }

    public String toString(){
        return "Router: " + this.name;
    }
}
