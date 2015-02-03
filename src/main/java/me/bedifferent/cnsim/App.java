package me.bedifferent.cnsim;

public class App {
    public static void main( String[] args ){
        Router a = new Router("R1");
        Router b = new Router("R2");
        Router c = new Router("R3");
        Router d = new Router("R4");
        
        a.addNeighbour(b);
        a.addNeighbour(c);
        b.addNeighbour(d);
        b.addNeighbour(c);
        b.addNeighbour(a);
        c.addNeighbour(d);
        c.addNeighbour(b);
        c.addNeighbour(a);
        d.addNeighbour(b);
        d.addNeighbour(c);

        System.out.println("Network created");
        a.requestResource(d, new Resource(), 100);

        System.out.println("Program fineshed");
    }
}
