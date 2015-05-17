package me.bedifferent.cnsim;

import java.util.*;

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

        public Router(String name, ICache cacheStrategy, long cacheDim){
            this.name = name;
            this.cacheDim = cacheDim;
            this.neighbours = new ArrayList<Router>();
            this.servers = new ArrayList<Server>();
            this.clients = new ArrayList<Client>();
            this.cache = new HashMap<Resource, Description>();
            this.datacollector = null;
            this.usedCache = 0;
            this.cacheStrategy = cacheStrategy;
        }

        public void addNeighbour(Router r){
            this.neighbours.add(r);
            //r.neighbours.add(this);
        }

        public void addServer(Server s){
            this.servers.add(s);
            s.addRouter(this);
        }

        public void addClient(Client c){
            this.clients.add(c);
            c.addRouter(this);
        }

        public void requestResource(Router source, Resource res, long time, RngStream r){
            if(this.cache.get(res).useResource(time)){
//                System.out.println(this + " Resource: " + res + " present in the cache");
                datacollector.pushData(new Event(Event.Type.HIT, res, this.toString()));
            }else{
 //               System.out.println(this + " Resource: " + res + " is not present");
                datacollector.pushData(new Event(Event.Type.MISS, res, this.toString()));
            // if I have the source router as neighbour then take the
            // resource from him directlly
            if(this.equals(source)){
  //              System.out.println(this + " I'm the source router");
            }else{
                if(this.neighbours.contains(source)){
                    source.requestResource(source, res, time, r);
                } else {
                    int req = r.randInt(0, this.neighbours.size()-1);
                    this.neighbours.get(req).requestResource(source, res, time, r);
                }
            }
            //System.out.println("Make available: "+this+" res: "+res+" at "+time);
            cacheStrategy.makeAvailable(this, res, time);
        }

        //System.out.println("-----------------------------");
        //System.out.println(this);
        //for(Resource re : this.cache.keySet()){
        //    System.out.println(re + " "+this.cache.get(re).isAvailable());
        //}
        //System.out.println("-----------------------------");
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
        this.usedCache = 0;
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
