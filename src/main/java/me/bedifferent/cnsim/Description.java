package me.bedifferent.cnsim;

public class Description{
    private boolean available;
    private long arrivedTime;
    private long lastTime;
    private long timerStartAt;

    public Description(){
        this.arrivedTime = 0;
        this.lastTime = 0;
        this.available = false;
        this.timerStartAt = 0;
    }

    public void setAvailable(boolean available, long time){
        if(!this.available && available){
            this.arrivedTime = time;
        }
        this.available = available;
    }

    public boolean isAvailable(){
        return this.available;
    }

    public boolean useResource(long time){
        // The user must first controll if the resource is available
        if(this.isAvailable()){
            this.lastTime = time;
            return true;
        }else
            return false;
    }

    public void removeResource(){
        this.lastTime = 0;
        this.arrivedTime = 0;
    }

    public void createResource(long time){
        this.lastTime = time;
        this.arrivedTime = time;
    }
    
    public long getArrivedTime(){
        return this.arrivedTime;
    }

    public long getLastUsedTime(){
        return this.lastTime;
    }

    public long getTimerStartedAt(){
        return this.timerStartAt;
    }

    public void resetTimer(long time){
        this.timerStartAt = time;
    }
}
