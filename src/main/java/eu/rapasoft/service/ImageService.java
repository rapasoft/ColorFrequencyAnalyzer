package eu.rapasoft.service;

import eu.rapasoft.exception.ImageFileException;
import javafx.scene.image.Image;

import javax.enterprise.context.ApplicationScoped;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
@ApplicationScoped
public class ImageService {

    private static final List<String> SUPPORTED_EXTENSIONS = Arrays.asList("PNG", "JPG", "JPEG", "BMP", "GIF");

    public static String toHex(int r, int g, int b) {
        return "#" + toBrowserHexValue(r) + toBrowserHexValue(g) + toBrowserHexValue(b);
    }

    private static String toBrowserHexValue(int number) {
        StringBuilder builder = new StringBuilder(Integer.toHexString(number & 0xff));
        while (builder.length() < 2) {
            builder.append("0");
        }
        return builder.toString().toUpperCase();
    }

    public BufferedImage loadBufferedImage(File file) throws ImageFileException {
        checkFileExtension(file);
        try (FileInputStream inputStream = new FileInputStream(file)) {
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ImageFileException("Image file could not be loaded. " + e.getMessage());
        }
    }

    private void checkFileExtension(File file) throws ImageFileException {
        String[] filenameExtension = file.getName().split("\\.");
        if (filenameExtension.length != 2) {
            throw new ImageFileException("Invalid extension");
        } else if (!SUPPORTED_EXTENSIONS.contains(filenameExtension[1].toUpperCase())) {
            throw new ImageFileException("Extension not supported, only following file types are supported: " + SUPPORTED_EXTENSIONS);
        }
    }

    public Image loadFxImage(File file) throws ImageFileException {
        checkFileExtension(file);
        try (FileInputStream inputStream = new FileInputStream(file)) {
            return new Image(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ImageFileException("Image file could not be loaded. " + e.getMessage());
        }
    }

}
