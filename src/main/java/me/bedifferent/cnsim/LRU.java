package me.bedifferent.cnsim;

import java.util.Map;

public class LRU implements ICache{
    public void makeAvailable(Router router, Resource resource, long time){
        // let's see if the cache is full or not
        if(router.getCacheDim() < router.getUsedCache() + resource.getDim()){
            // free some space
            long toFree = resource.getDim() - router.getCacheDim() + router.getUsedCache();
            while(toFree > 0){
                Map<Resource, Description> cache = router.getCache();
                Resource smallest = null;
                for(Resource res : cache.keySet()){
                    Description d = cache.get(res);
                    if(smallest == null && d.isAvailable())
                        smallest = res;
                    if(d.isAvailable() && d.getLastUsedTime() <= cache.get(smallest).getLastUsedTime()){
                        smallest = res;
                    }
                }
                if(smallest == null){
                    for(Resource res:cache.keySet()){
                        System.out.println(res.toString()+cache.get(res).isAvailable());
                    } 
                }
                toFree -= smallest.getDim();
                router.setUsedCache(router.getUsedCache() - smallest.getDim());
                //System.out.println("making false"+smallest+" "+toFree);
                router.getCache().get(smallest).setAvailable(false, time);
            }
        }
        //System.out.println("making true"+resource);
        router.setUsedCache(router.getUsedCache() + resource.getDim());
        router.getCache().get(resource).setAvailable(true, time);
    }
}
