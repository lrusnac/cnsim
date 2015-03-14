package me.bedifferent.cnsim;

import java.util.List;
import java.util.ArrayList;

public class Server{
    /* This class have the memory, it's connected to a router */
    
    private List<Resource> memory;
    private List<Router> routers;
    private String name;

    public Server(String name){
        this.memory = new ArrayList<Resource>();
        this.routers = new ArrayList<Router>();
        this.name = name;
    }

    public void addRouter(Router r){
        this.routers.add(r);
    }
}
