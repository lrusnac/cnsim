package me.bedifferent.cnsim;

public class Event{
    private String description;
    public static enum Type {
        MISS, HIT, ANY
    }
    private Type type;

    public Event(Type type, String description){
        this.type = type;
        this.description = description;
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
