package ru.netology.graphics.image;

public class TextColorSecondary implements TextColorSchema{

    @Override
    public char convert(int color) {
        char[] colorChar = {'▇', '●', '◉', '◍', '◎', '○', '☉', '◌', '-'};
        return colorChar[(int) Math.floor(color / 256. * colorChar.length)];
        // точка после 256 означает что это как бы double, иначе int/int хрен знает как поделится
    }
}
