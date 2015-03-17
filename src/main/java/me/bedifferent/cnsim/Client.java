package me.bedifferent.cnsim;

import java.util.List;
import java.util.ArrayList;

import java.util.Random;

public class Client{
    private String name;
    private Router router;

    public Client(String name){
        this.name = name;
    }

    public void addRouter(Router r){
        this.router = r;
    }

    public void requestResource(Server source, Resource res, long time, Random r){
        this.requestResource(source.getRouter(), res, time, r);
    }
    
    public void requestResource(Router source, Resource res, long time, Random r){
        if(router == null){
            System.out.println("The client is not connected to a router");
        }else{
            router.requestResource(source, res, time, r);
        }
    }
}
