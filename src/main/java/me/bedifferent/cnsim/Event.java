package me.bedifferent.cnsim;

public class Event{
    private String description;
    public static enum Type {
        MISS, HIT, ANY
    }
    private Type type;
    private Resource resource;

    public Event(Type type, Resource res, String description){
        this.type = type;
        this.description = description;
        this.resource = res;
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

    public Resource getResource() {return this.resource;}

    public void setResource(Resource resource) {this.resource = resource;}
}
