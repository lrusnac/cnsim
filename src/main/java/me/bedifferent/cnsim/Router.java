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
    private long cacheDim; //in Mbyte
    private long usedCache;
    private Map<Resource, Description> cache;
    private String name;
    private DataCollector datacollector;
    private ICache cacheStrategy;

    public Router(String name, long cacheDim){
        this.name = name;
        this.cacheDim = cacheDim;
        this.neighbours = new ArrayList<Router>();
        this.servers = new ArrayList<Server>();
        this.clients = new ArrayList<Client>();
        this.cache = new HashMap<Resource, Description>();
        this.datacollector = null;
        this.usedCache = 0;
        this.cacheStrategy = new LRU();
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

    public void requestResource(Router source, Resource res, long time, Random r){
        if(this.cache.get(res).useResource(time)){
            System.out.println(this + " Resource present in the cache");
            datacollector.pushData(new Event(Event.Type.HIT));
        }else{
            System.out.println(this + " Resource is not present");
            datacollector.pushData(new Event(Event.Type.MISS));
            // if I have the source router as neighbour then take the
            // resource from him directlly
            if(this.equals(source)){
                System.out.println(this + " I'm the source router");
            }else{
                if(this.neighbours.contains(source)){
                    source.requestResource(source, res, time, r);
                } else {
                    int req = r.nextInt(this.neighbours.size());
                    this.neighbours.get(req).requestResource(source, res, time, r);
                }
            }
            cacheStrategy.makeAvailable(this, res);
            //this.cache.get(res).setAvailable(true, time);
        }
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

    public Map<Resource, Description> getCache(){
        return this.cache;
    }

    public long getCacheDim(){
        return this.cacheDim;
    }

    public long getUsedCache(){
        return this.usedCache;
    }

    public void setUsedCache(long usedCache){
        this.usedCache = usedCache;
    }
}
