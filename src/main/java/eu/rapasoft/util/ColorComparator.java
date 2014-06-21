package eu.rapasoft.util;

import java.awt.*;
import java.util.Comparator;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
public class ColorComparator implements Comparator<Color> {

    @Override
    public int compare(Color o1, Color o2) {
        return Integer.compare(o1.getRGB(), o2.getRGB());
    }
}
