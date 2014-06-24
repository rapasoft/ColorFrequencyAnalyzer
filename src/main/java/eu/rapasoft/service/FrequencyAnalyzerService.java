package eu.rapasoft.service;

import eu.rapasoft.exception.ImageFileException;
import eu.rapasoft.model.ColorFrequency;
import eu.rapasoft.util.ColorComparator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
@ApplicationScoped
public class FrequencyAnalyzerService {

    @Inject
    ImageService imageService;

    public SortedSet<ColorFrequency> computeFrequencies(File file) throws ImageFileException {
        BufferedImage image = imageService.loadBufferedImage(file);
        return calculateFrequenciesPerColor(image);
    }

    private SortedSet<ColorFrequency> calculateFrequenciesPerColor(BufferedImage image) {
        SortedSet<ColorFrequency> frequencies = new TreeSet<>();

        Map<Color, Integer> frequencyMap = new TreeMap<>(new ColorComparator());

        long totalPixelCount = 0;

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
                Color color = new Color(rgb);
                if (frequencyMap.containsKey(color)) {
                    frequencyMap.put(color, frequencyMap.get(color) + 1);
                } else {
                    frequencyMap.put(color, 1);
                }
                totalPixelCount++;
            }
        }

        for (Color color : frequencyMap.keySet()) {
            frequencies.add(new ColorFrequency(color, frequencyMap.get(color), totalPixelCount));
        }

        return frequencies;
    }

}
