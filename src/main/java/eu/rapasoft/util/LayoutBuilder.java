package eu.rapasoft.util;

import eu.rapasoft.model.ColorFrequency;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.Set;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
public class LayoutBuilder {

    public static void appendToLayout(Set<ColorFrequency> frequencies, GridPane inputGridPane) {
        int row = 2;
        int column = 0;
        for (ColorFrequency colorFrequency : frequencies) {
            HBox hBox = new HBox();
            javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle(50, 50);
            Color color = colorFrequency.getColor();
            javafx.scene.paint.Color colorRGB = javafx.scene.paint.Color.color(color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0);

            rectangle.setFill(colorRGB);
            Text text = new Text(colorFrequency.getFrequencyInPercentage());
            hBox.getChildren().addAll(rectangle, text);
            inputGridPane.add(hBox, column, row);
            column++;
            if (column == 5) {
                row++;
                column = 0;
            }
        }
    }

}
