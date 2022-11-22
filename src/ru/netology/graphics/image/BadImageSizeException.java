package ru.netology.graphics.image;

public class BadImageSizeException extends Exception {

    protected double maxRatio;
    public BadImageSizeException(double ratio, double maxRatio) {
        super("Максимальное соотношение сторон изображения " + maxRatio + ", а у этой " + ratio);
    }

    public BadImageSizeException() {

    }

    public void setMaxRatio (double maxRatio) {
        this.maxRatio = maxRatio;
    }

    public double getMaxRatio () {
        return maxRatio;
    }
}
