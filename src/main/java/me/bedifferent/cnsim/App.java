package me.bedifferent.cnsim;

import java.util.Properties;
import java.io.InputStream;

public class App {
    public static void main( String[] args ) throws Exception{
        Engine e = new Engine();
        e.run(20, 11021992);
        // n.getClient().requestResource(n.getServer(), n.getResByName("prova"), 100);
        
        // testing properties
        Properties prop = new Properties();
        String propFileName = "/config.properties";

        InputStream inputStream = App.class.getResourceAsStream(propFileName);

        if(inputStream != null){
            prop.load(inputStream);
        }else{
            System.out.println("Property file "+propFileName+" not found");
            return;
        }

        String user = prop.getProperty("user");
        System.out.println("The property 'user' has value: "+user);
    }
}
