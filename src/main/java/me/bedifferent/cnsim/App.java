package me.bedifferent.cnsim;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class App {
    public static void main( String[] args ) throws Exception{
    	// Reading the properties
        Properties prop = new Properties();
        String propFileName = "/config.properties";

        InputStream inputStream = App.class.getResourceAsStream(propFileName);

        if(inputStream != null){
            prop.load(inputStream);
        }else{
            System.out.println("Property file "+propFileName+" not found");
            return;
        }

        
        
        Map<String, String> properties = new HashMap<String, String>();
        properties.put("seed", prop.getProperty("seed"));
        properties.put("iterations", prop.getProperty("iterations"));
        properties.put("resource_file", prop.getProperty("resource_file"));
        properties.put("resource_number", prop.getProperty("resource_number"));
        properties.put("resource_dimension", prop.getProperty("resource_dimension"));
        properties.put("cache_strategy", prop.getProperty("cache_strategy"));
        properties.put("cache_dimension", prop.getProperty("cache_dimension"));
        properties.put("network_topology", prop.getProperty("network_topology"));
        properties.put("collectors", prop.getProperty("collectors"));
        properties.put("confidence_interval_level", prop.getProperty("confidence_interval_level"));
        
        
        // Creating the engine and run the simulator
        Engine e = new Engine(properties);
        e.run(Integer.parseInt(properties.get("iterations")), Integer.parseInt(properties.get("seed")));
        // e.run(20, 11021992);
    }
}
