package me.bedifferent.cnsim;

public class Resource{
    private int dim; //in mb    
    private String name;

    public Resource(String name, int dim){
        this.name = name;
        this.dim = dim;
    }

    public boolean equals(Object o){
        if(o instanceof Resource){
            return ((Resource)o).name.equals(this.name) && ((Resource)o).dim == this.dim;
        }
        return false;
    }

    public String getName(){
        return this.name;
    }

    public int getDim(){
        return this.dim;
    }
}
