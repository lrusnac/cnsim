package me.bedifferent.cnsim;

import java.util.List;
import java.util.Map;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Random;

public class Router{
    private List<Router> neighbours;
    private List<Server> servers;
    private List<Client> clients;
    private int cacheDim; //in Mbyte
    private Map<Resource, Description> cache;
    private String name;
    private DataCollector datacollector;

    public Router(String name){
        this.name = name;
        this.neighbours = new ArrayList<Router>();
        this.servers = new ArrayList<Server>();
        this.clients = new ArrayList<Client>();
        this.cache = new HashMap<Resource, Description>();
        this.datacollector = null;
    }

    public void addNeighbour(Router r){
        this.neighbours.add(r);
        r.neighbours.add(this);
    }

    public void addServer(Server s){
        this.servers.add(s);
        s.addRouter(this);
    }

    public void addClient(Client c){
        this.clients.add(c);
        c.addRouter(this);
    }

    public boolean requestResource(Router source, Resource res, long time, Random r){
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
                return source.requestResource(source, res, time, r);
            } else {
                int req = r.nextInt(this.neighbours.size());
                return this.neighbours.get(req).requestResource(source, res, time, r);
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

    public void setDataCollector(DataCollector dc){
        this.datacollector = dc;
    }

    public void setCache(List<Resource> cache){
        for(Resource res:cache){
            this.cache.put(res, new Description());
        }
    }

    public void resetCache(){
        for(Resource res : this.cache.keySet()){
            this.cache.put(res, new Description());
        }
    }
}
