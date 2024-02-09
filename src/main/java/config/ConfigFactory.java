package config;

public class ConfigFactory {
    private ConfigFactory(){
    }

    public static FrameworkConfig getConfig(){
        return org.aeonbits.owner.ConfigFactory.create(FrameworkConfig.class);
    }
}
