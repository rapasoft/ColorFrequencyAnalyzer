package eu.rapasoft.service;

import eu.rapasoft.exception.ImageFileException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * User: Pavol Rajzák
 * Date: 20.3.2014
 * Time: 21:04
 */
@RunWith(value = JUnit4.class)
public class ImageServiceTest {

    private ImageService imageService = new ImageService();

    @Test
    public void testLoadBufferedImageFile() throws IOException, ImageFileException {
        URL filePath = getClass().getClassLoader().getResource("./wordle_lastfm_chart.PNG");
        Assert.assertTrue(filePath != null);
        Assert.assertNotNull(imageService.loadBufferedImage(new File(filePath.getPath())));
    }

    @Test
    public void testLoadFxImage() throws IOException, ImageFileException {
        URL filePath = getClass().getClassLoader().getResource("./wordle_lastfm_chart.PNG");
        Assert.assertTrue(filePath != null);
        Assert.assertNotNull(imageService.loadBufferedImage(new File(filePath.getPath())));
    }

    @Test(expected = ImageFileException.class)
    public void testUnsupportedExtension() throws IOException, ImageFileException {
        URL filePath = getClass().getClassLoader().getResource("./test.me");
        Assert.assertTrue(filePath != null);
        imageService.loadBufferedImage(new File(filePath.getPath()));
    }

    @Test
    public void testCheckValidFileExtension() throws ImageFileException {
        imageService.checkFileExtension(new File("hooray.png"));
    }

    @Test(expected = ImageFileException.class)
    public void testCheckInvalidFileExtension() throws ImageFileException {
        imageService.checkFileExtension(new File("hooray.wow"));
    }

    @Test
    public void testToHex() {
        Assert.assertEquals("#000000", ImageService.toHex(0, 0, 0));
        Assert.assertEquals("#FFFFFF", ImageService.toHex(255, 255, 255));
        Assert.assertEquals("#7BD459", ImageService.toHex(123, 212, 89));

        Assert.assertEquals("#NaN", ImageService.toHex(123, 456, 789));
        Assert.assertEquals("#NaN", ImageService.toHex(-123, -456, 789));
    }

}
