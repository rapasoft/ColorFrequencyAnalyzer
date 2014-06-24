package eu.rapasoft.util;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
@ApplicationScoped
public class ApplicationProperties {

    private static final String VERSION = "version";
    private static final String AUTHOR = "author";
    private static final String APPLICATION_PROPERTIES_FILE_PATH = "/application.properties";

    private Properties properties = new Properties();

    private Logger logger = Logger.getLogger(ApplicationProperties.class.getName());

    @PostConstruct
    public void init() {
        try (InputStream is = getClass().getResourceAsStream(APPLICATION_PROPERTIES_FILE_PATH)) {
            properties.load(is);
        } catch (IOException e) {
            logger.severe("Could not open application.properties file: " + e.getMessage());
        }
    }

    public String getApplicationVersion() {
        return (String) properties.get(VERSION);
    }

    public String getAuthor() {
        return (String) properties.get(AUTHOR);
    }

}