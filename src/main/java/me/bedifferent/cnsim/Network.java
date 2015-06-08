package me.bedifferent.cnsim;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Network{
    private List<Router> r;
    private List<Client> c;
    private List<Server> s;
    private List<Resource> memory;
    
    private Map<String, String> properties;
    
    public Network(Map<String, String> properties){
    	this.properties = properties;
    	
		String cacheStrategy = "me.bedifferent.cnsim." + this.properties.get("cache_strategy");
		Integer cacheDimension = Integer.parseInt(this.properties.get("cache_dimension"));
		
		Properties prop = new Properties();
        String propFileName = this.properties.get("network_topology");
        
        InputStream inputStream = Network.class.getResourceAsStream(propFileName);

        try {   
            if(inputStream != null){
                prop.load(inputStream);
            }else{
                System.out.println("Property file "+propFileName+" not found");
                return;
            }
        }catch(Exception e){
        	System.out.println("[Error] Be sure to locate the file in the right directory!");
        	e.printStackTrace();
        	return;
        }
    		
		// ROUTERS
        String routers[] = prop.getProperty("routers").split(",");
		r = new LinkedList<Router>();
		
        for(int i=0; i<routers.length; i++) {
        	try {
				r.add(i, new Router(routers[i], (ICache) Class.forName(cacheStrategy).newInstance(), cacheDimension));
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        // CLIENTS
        String clients[] = prop.getProperty("clients").split(",");
		c = new LinkedList<Client>();

        for(int i=0; i<clients.length; i++) {
        	c.add(i, new Client(clients[i]));
        }
        
        // SERVERS
        String servers[] = prop.getProperty("servers").split(",");
		s = new LinkedList<Server>();

        for(int i=0; i<servers.length; i++) {
        	s.add(i, new Server(servers[i]));
        }
        
        
        // ASSOCIATIONS FOR ROUTERS
        for(int i=0; i<routers.length; i++) {
        	String[] neighbords = prop.getProperty(routers[i]).split(",");
        	for(int j=0; j<neighbords.length; j++) {
        		//search the neighboard type and object
        		boolean found = false;
        		if(!found){
	        		for(int k=0; k<routers.length; k++) {
	        			if(routers[k].equals(neighbords[j])) {
	        				r.get(i).addNeighbour(r.get(k));
	        				found = true;
	        				break;
	        			}
	        		}
        		}
        		
        		if(!found) {
        			for(int k=0; k<clients.length; k++) {
	        			if(clients[k].equals(neighbords[j])) {
	        				r.get(i).addClient(c.get(k));
	        				found = true;
	        				break;
	        			}
	        		}
        		}
        		
        		if(!found) {
        			for(int k=0; k<servers.length; k++) {
	        			if(servers[k].equals(neighbords[j])) {
	        				r.get(i).addServer(s.get(k));
	        				found = true;
	        				break;
	        			}
	        		}
        		}
        	}
        }
			
        this.memory = new ArrayList<Resource>();
    }

    public Client getClient(){
        return c.get(0);
    }

    public Server getServer(){
        return s.get(0);
    }

    public void setMemory(List<Resource> mem){
        this.memory = mem;

        for(int i=0; i<this.r.size(); i++) {
            r.get(i).setCache(mem);
        }
        for(int i=0; i<s.size(); i++)
            s.get(i).setMemory(mem);
    }

    public Resource getResByName(String name){
        for(Resource r : this.memory){
            if(r.getName().equals(name))
                return r;
        }
        return null;
    }

    public void setDataCollector(DataCollector dc){
        for(int i=0; i<this.r.size(); i++)
            this.r.get(i).setDataCollector(dc);
    }

    public void clearCache(){
        //clean the cache of the routers
        for(int i=0; i<r.size(); i++)
            r.get(i).resetCache();
    }
}
