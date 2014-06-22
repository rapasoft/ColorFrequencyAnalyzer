package eu.rapasoft.weld;

import javafx.fxml.FXMLLoader;
import javafx.util.Callback;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.io.IOException;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
public class WeldFXMLLoader {

    @Inject
    Instance<Object> instance;

    /**
     * Load FXML resource and {@link Inject} controller's members
     *
     * @param fxmlFileLocation
     */
    @SuppressWarnings("unchecked")
    public <T> T load(String fxmlFileLocation) {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(new ControllerFactoryCallback());

        try {
            return (T) loader.load(getClass().getResourceAsStream("/" + fxmlFileLocation));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private class ControllerFactoryCallback implements Callback<Class<?>, Object> {
        @Override
        public Object call(Class<?> param) {
            return instance.select(param).get();
        }
    }

}
