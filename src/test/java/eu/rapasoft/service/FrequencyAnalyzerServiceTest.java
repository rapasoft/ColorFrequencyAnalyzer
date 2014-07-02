package eu.rapasoft.service;

import eu.rapasoft.exception.ImageFileException;
import eu.rapasoft.model.ColorFrequency;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Set;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
public class FrequencyAnalyzerServiceTest {

    FrequencyAnalyzerService frequencyAnalyzerService;

    @Before
    public void init() {
        frequencyAnalyzerService = new FrequencyAnalyzerService();
        frequencyAnalyzerService.imageService = new ImageService();
    }

    @Test
    public void testComputeFrequenciesQuarters() throws ImageFileException {
        Set<ColorFrequency> colorFrequencySet = assertCorrectComputation("./test_img.png");

        Assert.assertFalse(colorFrequencySet.isEmpty());
        Assert.assertEquals(4, colorFrequencySet.size());

        for (ColorFrequency colorFrequency : colorFrequencySet) {
            Assert.assertEquals(colorFrequency.getColorHex() + ": " + colorFrequency.calculateFrequency(), 25.0, colorFrequency.calculateFrequency(), 1);
        }
    }

    @Test
    public void testComputeFrequenciesCross() throws ImageFileException {
        Set<ColorFrequency> colorFrequencySet = assertCorrectComputation("./test_img2.png");

        Assert.assertFalse(colorFrequencySet.isEmpty());
        Assert.assertEquals(4, colorFrequencySet.size());

        for (ColorFrequency colorFrequency : colorFrequencySet) {
            Assert.assertEquals(colorFrequency.getColorHex() + ": " + colorFrequency.calculateFrequency(), 25.0, colorFrequency.calculateFrequency(), 5);
        }
    }

    @Test
    public void testMultipleColorsPng() throws ImageFileException {
        assertCorrectComputation("./wordle_lastfm_chart.PNG");
    }

    @Test
    public void testMapAnalysis() throws ImageFileException {
        assertCorrectComputation("./poprad_stvorceky.PNG");
    }

    @Test
    public void testColorfulJpg() throws ImageFileException {
        assertCorrectComputation("./Tulips.jpg");
    }

    private Set<ColorFrequency> assertCorrectComputation(String fileName) throws ImageFileException {
        URL filePath = getClass().getClassLoader().getResource(fileName);
        Assert.assertNotNull(filePath);
        Set<ColorFrequency> colorFrequencySet = frequencyAnalyzerService.computeFrequencies(new File(filePath.getPath()));

        double total = 0;

        for (ColorFrequency colorFrequency : colorFrequencySet) {
            total += colorFrequency.calculateFrequency();
        }

        Assert.assertEquals("Total should be 100% (more or less).", 100.0, total, 1.0);

        Assert.assertNotNull(colorFrequencySet);
        Assert.assertFalse(colorFrequencySet.isEmpty());

        return colorFrequencySet;
    }


}
