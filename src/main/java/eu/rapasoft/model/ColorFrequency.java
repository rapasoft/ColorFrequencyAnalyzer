package eu.rapasoft.model;

import eu.rapasoft.service.ImageService;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Comparator;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
public class ColorFrequency {

    private Color color;
    private long numberOfOccurrences;
    private long totalPixelCount;

    public ColorFrequency(Color color, Integer numberOfOccurrences, long totalPixelCount) {
        this.color = color;
        this.numberOfOccurrences = numberOfOccurrences;
        this.totalPixelCount = totalPixelCount;
    }

    /**
     * @return The percentage of color in the whole image.
     */
    public double calculateFrequency() {
        return (numberOfOccurrences / (double) totalPixelCount) * 100;
    }

    /**
     * @return String representation of {@link ColorFrequency#calculateFrequency()} as a String rounded up to 2 decimals.
     */
    public String getFrequencyInPercentage() {
        NumberFormat numberFormat = new DecimalFormat();
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        return numberFormat.format(calculateFrequency()) + "%";
    }

    public long getNumberOfOccurrences() {
        return numberOfOccurrences;
    }

    public void mergeWith(ColorFrequency colorFrequency) {
        this.numberOfOccurrences += colorFrequency.getNumberOfOccurrences();
    }

    public Color getColor() {
        return color;
    }

    public String getColorHex() {
        return ImageService.toHex(color.getRed(), color.getGreen(), color.getBlue());
    }

    public static class ColorFrequencyReverseComparator implements Comparator<ColorFrequency> {

        @Override
        public int compare(ColorFrequency o1, ColorFrequency o2) {
            if (o2.calculateFrequency() != o1.calculateFrequency()) {
                return Double.compare(o2.calculateFrequency(), o1.calculateFrequency());
            } else {
                return o2.getColorHex().compareTo(o1.getColorHex());
            }
        }
    }

}
