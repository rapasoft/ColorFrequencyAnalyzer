package eu.rapasoft.model;

import eu.rapasoft.service.ImageService;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
public class ColorFrequency implements Comparable<ColorFrequency> {

    private Color color;
    private long numberOfOccurrences;
    private long totalPixelCount;

    public ColorFrequency(Color color, Integer numberOfOccurrences, long totalPixelCount) {
        this.color = color;
        this.numberOfOccurrences = numberOfOccurrences;
        this.totalPixelCount = totalPixelCount;
    }

    public double calculateFrequency() {
        return (numberOfOccurrences / (double) totalPixelCount) * 100;
    }

    public String getFrequencyInPercentage() {
        NumberFormat numberFormat = new DecimalFormat();
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        return numberFormat.format(calculateFrequency()) + "%";
    }

    public double getNumberOfOccurrences() {
        return numberOfOccurrences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColorFrequency that = (ColorFrequency) o;

        if (numberOfOccurrences != that.numberOfOccurrences) return false;
        if (totalPixelCount != that.totalPixelCount) return false;
        if (color != null ? !color.equals(that.color) : that.color != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + (int) (numberOfOccurrences ^ (numberOfOccurrences >>> 32));
        result = 31 * result + (int) (totalPixelCount ^ (totalPixelCount >>> 32));
        return result;
    }

    @Override
    public int compareTo(ColorFrequency other) {
        return Double.compare(this.getNumberOfOccurrences(), other.getNumberOfOccurrences());
    }

    public Color getColor() {
        return color;
    }

    public String getColorHex() {
        return ImageService.toHex(color.getRed(), color.getGreen(), color.getBlue());
    }
}
