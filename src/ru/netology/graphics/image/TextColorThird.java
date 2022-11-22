package ru.netology.graphics.image;

public class TextColorThird implements TextColorSchema {

    @Override
    public char convert(int color) {
        char[] colorChar = {'W', 'B', 'A', 'T', 'O', 'L', 'o', 'i'};
        int[] colorSteps = {32, 64, 96, 128, 160, 192, 224, 256};
        for (int i = 0; i < colorChar.length; i++) {
            if (color < colorSteps[i]) {
                return colorChar[i];
            }
        }
        return 32;
    }
}
