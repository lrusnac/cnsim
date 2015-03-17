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

        public Router getRouter(){
            if(this.routers.size() > 0){
                return this.routers.get(0);
            }
            return null;
        }
        
    public Resource getRandomResource(int x){
        return memory.get(x % this.memory.size());
    }

    public void setMemory(List<Resource> mem){
        this.memory = mem;
    }
}
