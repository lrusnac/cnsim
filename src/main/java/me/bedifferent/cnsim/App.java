package me.bedifferent.cnsim;

public class App {
    public static void main( String[] args ){
        Network n = new Network();
        System.out.println("Network created");
        
        n.getClient().requestResource(n.getServer(), n.getResByName("prova"), 100);
        
        System.out.println("Program fineshed");
    }
}
