package apavlikovskyi.spring_airport.dao.daoUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {


    public static Properties readProperties (String path){
        Properties properties = new Properties();
        try (InputStream is = PropertiesUtils.class
                .getClassLoader().getResourceAsStream(path)){
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}