package me.bedifferent.cnsim;

import java.util.ArrayList;
import java.util.List;

public class Network{
    private Router[] r;
    private Client[] c;
    private Server[] s;
    private List<Resource> memory;
    
    public Network(){
        //costruttore che mi crea la network di router
        r = new Router[4];
        c = new Client[2];
        s = new Server[2];
        r[0] = new Router("R1", new LRU(), 999);
        r[1] = new Router("R2", new LRU(), 999);
        r[2] = new Router("R3", new LRU(), 999);
        r[3] = new Router("R4", new LRU(), 999);
        
        r[0].addNeighbour(r[1]);
        //r[0].addNeighbour(r[2]);
        //r[1].addNeighbour(r[3]);
        r[1].addNeighbour(r[2]);
        r[2].addNeighbour(r[3]);

        c[0] = new Client("C1");
        c[1] = new Client("C2");

        r[0].addClient(c[0]);
        r[3].addClient(c[1]);

        s[0] = new Server("S1");
        s[1] = new Server("S2");

        r[0].addServer(s[0]);
        r[3].addServer(s[1]);

        this.memory = new ArrayList<Resource>();
    }

    public Client getClient(){
        return c[0];
    }

    public Server getServer(){
        return s[1];
    }

    public void setMemory(List<Resource> mem){
        this.memory = mem;

        for(int i=0; i<r.length; i++)
            r[i].setCache(mem);
        for(int i=0; i<s.length; i++)
            s[i].setMemory(mem);
    }

    public Resource getResByName(String name){
        for(Resource r : this.memory){
            if(r.getName().equals(name))
                return r;
        }
        return null;
    }

    public void setDataCollector(DataCollector dc){
        for(int i=0; i<this.r.length; i++)
            this.r[i].setDataCollector(dc);
    }

    public void clearCache(){
        //clean the cache of the routers
        for(int i=0; i<r.length; i++)
            r[i].resetCache();
    }
}
