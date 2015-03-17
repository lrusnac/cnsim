package me.bedifferent.cnsim;

import java.util.List;
import java.util.ArrayList;

public class Client{
    private String name;
    private List<Router> routers;

    public Client(String name){
        this.routers = new ArrayList<Router>();
        this.name = name;
    }

    public void addRouter(Router r){
        this.routers.add(r);
    }

    public void requestResource(Server source, Resource res, long time){
        this.requestResource(source.getRouter(), res, time);
    }
    
    public void requestResource(Router source, Resource res, long time){
        //routers.get(0).requestResource()
    }
}
