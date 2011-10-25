package com.stewsters;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


//http://www.mkyong.com/java/how-to-resize-an-image-in-java/
//http://www.hackerfactor.com/blog/index.php?/archives/432-Looks-Like-It.html

public class AveragePerceptualHash {

    static int IMG_WIDTH = 32;
    static int IMG_HEIGHT = 32;

    public static boolean[] hash(BufferedImage image) {

        BufferedImage smaller = resizeImage(image, BufferedImage.TYPE_INT_ARGB);

        int sum = 0;
        int cnt = 0;

        for (int height = 0; height < IMG_HEIGHT; height++) {
            for (int width = 0; width < IMG_WIDTH; width++) {
                int pixel = smaller.getRGB(width, height);
                int alpha = (pixel >> 24) & 0xff;
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                //average rgb to get brightness
                sum += (red + green + blue) / 3;
                cnt++;
            }
        }
        int average = sum / cnt;
        int index = 0;
        boolean[] result = new boolean[IMG_HEIGHT * IMG_WIDTH];

        for (int height = 0; height < IMG_HEIGHT; height++) {
            for (int width = 0; width < IMG_WIDTH; width++) {
                int pixel = smaller.getRGB(width, height);
                int alpha = (pixel >> 24) & 0xff;
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                //average rgb to get brightness
                int brightness = (red + green + blue) / 3;
                result[index] = brightness > average;
            }
        }
        return result;
    }


    private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
        return resizedImage;
    }

    public static float compare(boolean[] thing1, boolean[] thing2) {
        int matches = 0;
        int total = 0;
        for (int i = 0; i < thing1.length; i++) {
            if(thing1[i] == thing2[i]){
                matches ++;
            }
            total++;
        }
        return ((float)matches / (float)total);

    }

}