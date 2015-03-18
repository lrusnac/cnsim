package me.bedifferent.cnsim;

public class Event{
    private String description;
    public static enum Type {
        MISS, HIT
    }
    private Type type;

    public Event(Type type){
        this.type = type;
    }

    public void setType(Type type){
        this.type = type;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Type getType(){
        return this.type;
    }

    public String getDescription(){
        return this.description;
    }
}