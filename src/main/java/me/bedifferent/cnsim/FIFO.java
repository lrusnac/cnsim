package me.bedifferent.cnsim;

import java.util.Map;

public class FIFO implements ICache{
    public void makeAvailable(Router router, Resource resource, long time){
        // let's see if the cache is full or not
        if(router.getCacheDim() < router.getUsedCache() + resource.getDim()){
            // free some space
            long toFree = resource.getDim() - router.getCacheDim() + router.getUsedCache();
            while(toFree > 0){
                Map<Resource, Description> cache = router.getCache();
                Resource last = null;
                for(Resource res : cache.keySet()){
                    Description d = cache.get(res);
                    if(last == null && d.isAvailable())
                        last = res;
                    if(d.isAvailable() && d.getArrivedTime() <= cache.get(last).getArrivedTime()){
                        last = res;
                    }
                }
                toFree -= last.getDim();
                router.setUsedCache(router.getUsedCache() - last.getDim());
                //System.out.println("making false"+smallest+" "+toFree);
                router.getCache().get(last).setAvailable(false, time);
            }
        }
        //System.out.println("making true"+resource);
        router.setUsedCache(router.getUsedCache() + resource.getDim());
        router.getCache().get(resource).setAvailable(true, time);
    }
}
