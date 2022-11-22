package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TextGraphicConverterClass implements TextGraphicsConverter {

    protected double maxRatio = 0; // назначим 0 для проверки, вызван ли в main метод проверки соотношения сторон
    protected int maxWidth = 0;
    protected int maxHeight = 0;
    TextColorSchema shema = new TextColorPrimary();
    /** создание цветовой схемы здесь нужно для того, чтобы она у нас была по умолчанию, если не вызван метод
     * ее переопределения в main, иначе схемы не будет и изображение не сконвертируется */

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        int imgWidth;
        int imgHeight;
        BufferedImage img = ImageIO.read(new URL(url));
        imgHeight = img.getHeight();
        imgWidth = img.getWidth();
        double imgHeightD = imgHeight;
        double imgWidthD = imgWidth;
        double ratioWH = imgWidthD / imgHeightD; // соотношение ширины к высоте, чтобы картинка не была слигком широкой
        if (maxRatio > 0) { // если значение изменилось и больше не 0, значит метод вызван (считаю что пользователь разумный и не будет ставить соотношение < 0)
            if (ratioWH > maxRatio) {
                throw new BadImageSizeException(ratioWH, maxRatio);
            }
        }
        double ratioHW = imgHeightD / imgWidthD; // соотношение высоты к ширине, чтобы картинка не была слишком высокой
        if (maxRatio > 0) { // если значение изменилось и больше не 0, значит метод вызван (считаю что пользователь разумный и не будет ставить соотношение < 0)
            if (ratioHW > maxRatio) {
                throw new BadImageSizeException(ratioHW, maxRatio);
            }
        }
        if (maxWidth > 0) {
            if (maxWidth < imgWidth) {
                double widthD = maxWidth;
                imgWidth = (int) Math.round(imgWidthD * (widthD / imgWidthD));;
                imgHeight = (int) Math.round(imgHeightD * (widthD / imgWidthD));
            }
        }
        if (maxHeight > 0) {
            if (maxHeight < imgHeight) {
                double heightD = maxHeight;
                imgWidth = (int) Math.round(imgWidthD * (heightD / imgHeightD));;
                imgHeight = (int) Math.round(imgHeightD * (heightD / imgHeightD));
            }
        }
        Image scaledImage = img.getScaledInstance(imgWidth, imgHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_BYTE_GRAY );
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
//        ImageIO.write(bwImg, "png", new File("out.png"));
//        ImageIO.write(img, "png", new File("out1.png"));
        WritableRaster bwRaster = bwImg.getRaster();
        String line2 = "";

        for (int h = 0; h < imgHeight; h++) {
            StringBuilder line = new StringBuilder();

            for (int w = 0; w < imgWidth; w++) {
                int color = bwRaster.getPixel(w, h, new int[3])[0];
                char c = shema.convert(color);
                line.append("" + c + c);
            }
            line2 += line + "\n";
         }
        return line2;
    }

    @Override
    public void setMaxWidth(int width) {
        this.maxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.shema = schema;
    }
}
