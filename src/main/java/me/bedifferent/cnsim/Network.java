package me.bedifferent.cnsim;

import java.util.Map;

public class Network{
    private Router[] r;
    public Network(){
        //costruttore che mi crea la network di router
        r = new Router[4];
        r[0] = new Router("R1");
        r[1] = new Router("R2");
        r[2] = new Router("R3");
        r[3] = new Router("R4");
        
        r[0].addNeighbour(r[1]);
        r[0].addNeighbour(r[2]);
        r[1].addNeighbour(r[3]);
        r[1].addNeighbour(r[2]);
        r[1].addNeighbour(r[0]);
        r[2].addNeighbour(r[3]);
        r[2].addNeighbour(r[1]);
        r[2].addNeighbour(r[0]);
        r[3].addNeighbour(r[1]);
        r[3].addNeighbour(r[2]);
    }

    public Router getClient(){
        return this.r[0];
    }

    public Router getServer(){
        return this.r[3];
    }

    public void setMemory(Map<Resource, Description> mem){
        
    }
}
