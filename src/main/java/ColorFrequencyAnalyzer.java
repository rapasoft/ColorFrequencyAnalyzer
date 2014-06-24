import javafx.application.Application;
import javafx.stage.Stage;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;


/**
 * User: Pavol Rajzák
 * Date: 20.3.2014
 * Time: 21:52
 */
public class ColorFrequencyAnalyzer extends Application {


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(final Stage stage) {
        final Weld weld = new Weld();
        WeldContainer weldContainer = weld.initialize();
        Runtime.getRuntime().addShutdownHook(new ShutdownHook(weld));

        weldContainer.event().select(Stage.class).fire(stage);
    }

    static class ShutdownHook extends Thread {

        private final Weld weld;

        ShutdownHook(Weld weld) {
            this.weld = weld;
        }

        public void run() {
            weld.shutdown();
        }

    }


}
