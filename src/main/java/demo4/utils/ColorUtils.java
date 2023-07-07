package demo4.utils;

import java.awt.*;

public class ColorUtils {
    /**
     * 获取对比度明显的颜色
     */
    public static Color contrastColor(Color originalColor){
        return new Color(255-originalColor.getRed(),255-originalColor.getGreen(),255-originalColor.getBlue());
    }
}
