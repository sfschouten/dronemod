package sfschouten.dronemod.configuration;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {

	public static void init(File configFile){
		//Create the configuration object from the given configuration file.
		Configuration config = new Configuration(configFile);
		
		try{
			//Load the configuration file.
			config.load();
			
			//Read in properties from configuration file
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//Save the configuration file
			config.save();
		}
	}
}
