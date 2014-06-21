package eu.rapasoft.service;

import javafx.scene.image.Image;

import javax.enterprise.context.ApplicationScoped;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
@ApplicationScoped
public class ImageService {

    public BufferedImage loadBufferedImage(File file) {
        try (FileInputStream inputStream = new FileInputStream(file);) {
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public Image loadFxImage(File file) {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            return new Image(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
