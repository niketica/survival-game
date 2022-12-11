package nl.aniketic.survival.game.common;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageUtil {

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(ImageUtil.class.getResourceAsStream(path));
        } catch (IOException e) {
            throw new IllegalStateException("Could not load image.", e);
        }
    }

    public static BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }

    public static BufferedImage rotate(BufferedImage original, int degrees) {
        int width = original.getWidth();
        int height = original.getHeight();
        BufferedImage rotatedImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = rotatedImage.createGraphics();
        g2.rotate(Math.toRadians(degrees), width / 2, height / 2);
        g2.drawImage(original, null, 0, 0);
        g2.dispose();
        return rotatedImage;
    }

    private ImageUtil() {
        // Hide constructor
    }
}
