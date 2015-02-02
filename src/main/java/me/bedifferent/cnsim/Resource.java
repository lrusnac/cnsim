package me.bedifferent.cnsim;

public class Resource{
    private int dim; //in mb    
    private String name;


    public boolean equals(Object o){
        if(o instanceof Resource){
            return ((Resource)o).name.equals(this.name);
        }
        return false;
    }


}
