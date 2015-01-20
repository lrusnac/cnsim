package me.bedifferent.cnsim;

import java.util.List;
import java.util.Map;

import java.util.ArrayList;
import java.util.HashMap;

public class Router{
    private List<Router> neighbours;
    private int cacheDim; //in Mbyte
    private Map<Resource, Boolean> cache;
    private String name;

    public Router(String name){
        this.name = name;
        this.neighbours = new ArrayList<Router>();
        this.cache = new HashMap<Resource, Boolean>();
    }

    public void addNeighbour(Router r){
        this.neighbours.add(r);
    }

    public void requestResource(Router source, Resource res){
        //get a resource from a net
    }

    public String toString(){
        return "Router: " + this.name;
    }
}
