package demo4.ui.view;

public interface MainFrameBuilder {
    /**
     * 运行窗口
     */
    void start();
    /**
     * 初始化UI组件
     */
    void initComponents();
    /**
     * 初始化按钮组
     */
    MainFrameBuilder initButtonGroup();
    /**
     * 初始化画笔大小下拉框的数据
     */
    MainFrameBuilder initSizeBoxData();
    /**
     * 初始化画笔颜色选择器
     */
    MainFrameBuilder initColorSelectData();
    /**
     * 激活初始化画笔配置
     */
    MainFrameBuilder activateDefaultPaintConfig();
    /**
     * 初始化画笔风格
     */
    MainFrameBuilder initBrushStyleData();
    /**
     * 初始化橡皮擦
     */
    MainFrameBuilder initEraseData();
    /**
     * 初始化画布
     */
    MainFrameBuilder initCanvas();
    /**
     * 激活默认的frame配置
     */
    MainFrameBuilder activateFrameConfig();
    /**
     * 初始化主题菜单栏数据
     */
    MainFrameBuilder initThemeMenuData();
    /**
     * 初始化导入菜单栏数据
     */
    MainFrameBuilder initImportMenuData();
    /**
     * 初始化导出菜单栏数据
     */
    MainFrameBuilder initOutportMenuData();
    /**
     * 初始化插入文字功能
     */
    MainFrameBuilder initInsertTextData();
    /**
     * 初始化redo undo
     */
    MainFrameBuilder initRecoveryBtnData();
}
