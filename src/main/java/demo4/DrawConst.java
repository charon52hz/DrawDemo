package demo4;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface DrawConst {

    /**
     * 主题枚举
     *
     */
    @AllArgsConstructor
    @Getter
    enum ThemeEnum {
        DEFAULT_LIGHT(1,"默认明亮","",true),
        DEFAULT_DARK(2,"默认暗黑","",false),
        ArcOrange(3,"Arc Orange","com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme",true),
        ArcDarkOrange(4,"Arc Dark Orange","com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme",false),
        CyanLight(5,"Cyan Light","com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme",true),
        OneDark(7,"One Dark","com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme",false),
        MaterialDesignDark(6,"Material Design Dark","com.formdev.flatlaf.intellijthemes.FlatMaterialDesignDarkIJTheme",false),
        SolarizedLight(8,"Solarized Light","com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme",true),
        SolarizedDark(9,"Solarized Dark","com.formdev.flatlaf.intellijthemes.FlatSolarizedDarkIJTheme",false);
        private final Integer value;
        private final String remark;
        private final String classPath;
        private final Boolean isLight;
        /**
         * 判断是否是默认主题
         */
        public static boolean isDefaultTheme(ThemeEnum theme){
            return theme.equals(DEFAULT_LIGHT) || theme.equals(DEFAULT_DARK);
        }
        /**
         * 通过value获取枚举对象
         */
        public static ThemeEnum getByRemark(String remark) {
            for (ThemeEnum item : ThemeEnum.values()) {
                if (remark.equals(item.remark)) {
                    return item;
                }
            }
            return null;
        }
        /**
         * 获取下拉框列表数据
         */
        public static List<String> getRemarks() {
            List<String> list = new ArrayList<>();
            Arrays.asList(ThemeEnum.values()).forEach(item -> list.add(item.remark));
            return list;
        }
    }
    /**
     * 画笔风格枚举
     */
    @AllArgsConstructor
    @Getter
    enum BrushStyleEnum {

        ERASE(0, "橡皮擦"),
        PEN_MAO(1, "毛笔"),
        PEN_LA(2, "蜡笔"),
        PEN_QIAN(3, "铅笔"),
        WORD(4, "文字"),
        RECT(5, "矩形"),
        RECT_FILL(6, "填充矩形"),
        CIRCLE(6, "圆形"),
        CIRCLE_FILL(7, "填充圆形"),
        LINE(8, "直线"),
        LINE_DOT(9, "坐标系"),
        ROUND(10,"圆角矩形"),
        ROUND_FILL(11,"圆角填充矩形");


        private final Integer value;
        private final String remark;

        /**
         * 通过value获取枚举对象
         */
        public static BrushStyleEnum getByValue(Integer value) {
            for (BrushStyleEnum item : BrushStyleEnum.values()) {
                if (value.equals(item.value)) {
                    return item;
                }
            }
            return null;
        }
    }

    /**
     * 画笔粗细枚举，从1-10十个等级
     */
    @AllArgsConstructor
    @Getter
    enum BrushSizeEnum {
        SMALL(2),
        MIDDLE(5),
        BIG(8),
        LEVEL01(1),
        LEVEL02(2),
        LEVEL03(3),
        LEVEL04(4),
        LEVEL05(5),
        LEVEL06(6),
        LEVEL07(7),
        LEVEL08(8),
        LEVEL09(9),
        LEVEL10(10);
        private final Integer value;

        /**
         * 通过value获取枚举对象
         */
        public static BrushSizeEnum getByValue(Integer value) {
            for (BrushSizeEnum item : BrushSizeEnum.values()) {
                if (value.equals(item.value)) {
                    return item;
                }
            }
            return null;
        }

        /**
         * 获取下拉框列表数据
         * @return
         */
        public static List<Integer> getLevels() {
            List<Integer> list = new ArrayList<>();
            Arrays.asList(BrushSizeEnum.values()).forEach(item -> {
                if (item.name().contains("LEVEL")) {
                    list.add(item.value);
                }
            });
            return list;
        }
    }
}
