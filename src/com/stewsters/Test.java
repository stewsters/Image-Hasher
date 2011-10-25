package com.stewsters;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import com.stewsters.AveragePerceptualHash;

public class Test {

    public static void main(String[] args) {
        try {
            BufferedImage originalImage = ImageIO.read(new File("/home/stewsters/Pictures/cat.jpg"));
            boolean[] thing1 = AveragePerceptualHash.hash(originalImage);

            BufferedImage newImage = ImageIO.read(new File("/home/stewsters/Pictures/cat2.jpg"));
            boolean[] thing2 = AveragePerceptualHash.hash(newImage);


            System.out.println(AveragePerceptualHash.compare(thing1,thing2));

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


}
