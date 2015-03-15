package me.bedifferent.cnsim;

import java.util.List;
import java.util.ArrayList;

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
        r[0] = new Router("R1");
        r[1] = new Router("R2");
        r[2] = new Router("R3");
        r[3] = new Router("R4");
        
        r[0].addNeighbour(r[1]);
        r[0].addNeighbour(r[2]);
        r[1].addNeighbour(r[3]);
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
    }

    public Resource getResByName(String name){
        for(Resource r : this.memory){
            if(r.getName().equals(name))
                return r;
        }
        return null;
    }
}
