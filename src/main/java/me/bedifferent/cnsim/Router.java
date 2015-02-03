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
    private Random r;

    public Router(String name){
        this.name = name;
        this.neighbours = new ArrayList<Router>();
        this.cache = new HashMap<Resource, Description>();
        this.r = new Random();
    }

    public void addNeighbour(Router r){
        this.neighbours.add(r);
    }

    public boolean requestResource(Router source, Resource res, long time){
        //get a resource from a net
        if(this.equals(source)){
            System.out.println(this + " I'm the source router");
            return true; //TO-DO
        }
        
        
        /*if(this.cache.get(res).useResource(time)){
            System.out.println(this + " Resource present in the cache");
            return true;
        }else{*/
            System.out.println(this + " Resource is not present");
            // if I have the source router as neighbour then take the
            // resource from him directlly
            if(this.neighbours.contains(source)){
                return source.requestResource(source, res, time);
            } else {
                int req = this.r.nextInt(this.neighbours.size());
                return this.neighbours.get(req).requestResource(source, res, time);
            }
        //}
    }

    public boolean equals(Object o){
        if(o instanceof Router){
            return ((Router) o).name.equals(this.name);
        }
        return false;
    }

    public String toString(){
        return "Router: " + this.name;
    }
}
