package me.bedifferent.cnsim;

import java.util.Map;

public class TTL implements ICache{
    public void makeAvailable(Router router, Resource resource, long time){
        int timer = 50;
    	
    	do{
    		Map<Resource, Description> cache = router.getCache();
    		for(Resource res: cache.keySet()){
    			Description descr = cache.get(res);
    			if(descr.isAvailable() && (time - descr.getLastUsedTime()) > timer){
    				descr.setAvailable(false, time);
    				router.setUsedCache(router.getUsedCache() - res.getDim());
    			}
    		}
    		timer = timer - 1;
    	}while(router.getCacheDim() < router.getUsedCache() + resource.getDim());
    	
        router.setUsedCache(router.getUsedCache() + resource.getDim());
        router.getCache().get(resource).setAvailable(true, time);
    }
}
