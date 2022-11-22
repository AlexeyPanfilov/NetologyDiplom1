package ru.netology.graphics.image;

public class TextColorPrimary implements TextColorSchema {

    @Override
    public char convert(int color) {
        char[] colorChar = {35, 36, 64, 37, 42, 43, 45, 39};
        int[] colorSteps = {32, 64, 96, 128, 160, 192, 224, 256};
        for (int i = 0; i < colorChar.length; i++) {
            if (color < colorSteps[i]) {
                return colorChar[i];
            }
        }
        return 32;
    }
}
