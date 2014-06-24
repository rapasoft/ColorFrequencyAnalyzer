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

    public static final int SQUARE_SIZE = 60;
    public static final int START_ROW = 2;
    public static final int START_COLUMN = 0;
    public static final double MAX_RGB_COLOR = 255.0;

    public static void appendToLayout(Set<ColorFrequency> frequencies, GridPane inputGridPane, double numberOfColumns) {
        int row = START_ROW;
        int column = START_COLUMN;

        for (ColorFrequency colorFrequency : frequencies) {
            HBox hBox = new HBox();

            javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle(SQUARE_SIZE, SQUARE_SIZE);
            Color color = colorFrequency.getColor();
            javafx.scene.paint.Color colorRGB = javafx.scene.paint.Color.color(color.getRed() / MAX_RGB_COLOR, color.getGreen() / MAX_RGB_COLOR, color.getBlue() / MAX_RGB_COLOR);
            rectangle.setFill(colorRGB);

            Text percentage = new Text(colorFrequency.getFrequencyInPercentage());
            percentage.setWrappingWidth(SQUARE_SIZE);

            Text colorHex = new Text(colorFrequency.getColorHex());
            colorHex.setWrappingWidth(SQUARE_SIZE);

            hBox.getChildren().addAll(rectangle, colorHex, percentage);

            inputGridPane.add(hBox, column, row);
            column++;

            if (column == numberOfColumns) {
                row++;
                column = 0;
            }
        }
    }

}
