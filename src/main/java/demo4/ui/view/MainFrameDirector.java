package demo4.ui.view;

public class MainFrameDirector {

    public static MainFrameBuilder create(MainFrameBuilder frameBuilder) {
        // 注意先后顺序

        // * 来自JFromDesigner生成
        frameBuilder.initComponents();
        // * 初始化画布组件，自行填充组件，因为JFromDesigner不支持Canvas
        frameBuilder.initCanvas()
                //===========================数据与事件初始化=============================
                // * 初始化各组件数据
                // ** 初始化按钮组
                .initButtonGroup()
                // ** 初始化画笔大小的下拉框
                .initSizeBoxData()
                // ** 初始化颜色选择器
                .initColorSelectData()
                // ** 初始化画笔风格
                .initBrushStyleData()
                // ** 初始化橡皮擦
                .initEraseData()
                // ** 初始化插入文本框
                .initInsertTextData()
                // ** 初始化恢复按钮
                .initRecoveryBtnData()
                // * 初始化主题菜单栏数据
                .initThemeMenuData()
                // * 初始化导出菜单栏数据
                .initOutportMenuData()
                // * 初始化导入菜单栏数据
                .initImportMenuData()
                //===========================配置初始化==============================
                // * 激活初始化画笔配置
                .activateDefaultPaintConfig()
                // * 激活frame的默认配置
                .activateFrameConfig();

        frameBuilder.start();

        return frameBuilder;
    }

}
