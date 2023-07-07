package demo4.ui.view;

import demo4.DrawConst;
import demo4.command.Command;
import demo4.command.ShapeCommand;
import demo4.config.PaintConfig;
import demo4.listener.DrawListener;
import demo4.ui.canvas.MyCanvas;
import demo4.ui.trans.input.BackgroundImportFactory;
import demo4.ui.trans.input.FileImportFactory;
import demo4.ui.trans.output.FileOutputFactory;
import demo4.ui.trans.output.JPGOutputFactory;
import demo4.ui.trans.output.PNGOutputFactory;
import demo4.utils.CanvasUtils;
import demo4.utils.ColorUtils;
import lombok.Getter;
import lombok.Setter;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.List;

public class MainFrame extends JFrame implements MainFrameBuilder {

    @Getter
    @Setter
    private JPanel canvas;

    @Getter
    private final MainFrame frame;

    private final PaintConfig paintConfig = PaintConfig.getInstance();


    private final ButtonGroup sizeGroup = new ButtonGroup();
    private final ButtonGroup styleGroup = new ButtonGroup();
    private final ButtonGroup colorGroup = new ButtonGroup();


    public MainFrame() {
        // * 将类对象绑定到frame上,事件处理中替换this提升可读性
        this.frame = this;
    }


    @Override
    public MainFrameBuilder initSizeBoxData() {
        List<Integer> levels = DrawConst.BrushSizeEnum.getLevels();
        frame.sizeBox.setModel(new DefaultComboBoxModel<>(levels.toArray()));

        // 处理快捷按钮
        frame.smallSizeRadio.addActionListener(e -> {
            frame.paintConfig.setSize(DrawConst.BrushSizeEnum.SMALL);
            frame.sizeBox.setSelectedItem(DrawConst.BrushSizeEnum.SMALL.getValue());
        });
        frame.middleSizeRadio.addActionListener(e -> {
            frame.paintConfig.setSize(DrawConst.BrushSizeEnum.MIDDLE);
            frame.sizeBox.setSelectedItem(DrawConst.BrushSizeEnum.MIDDLE.getValue());
        });
        frame.bigSizeRadio.addActionListener(e -> {
            frame.paintConfig.setSize(DrawConst.BrushSizeEnum.BIG);
            frame.sizeBox.setSelectedItem(DrawConst.BrushSizeEnum.BIG.getValue());
        });

        // 处理下拉框
        sizeBox.addItemListener(e -> {
            // 只处理选中事件，因为每次点击会触发两次事件
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Integer item = (Integer) e.getItem();
                DrawConst.BrushSizeEnum sizeEnum = DrawConst.BrushSizeEnum.getByValue(item);
                if (sizeEnum == null || sizeEnum.equals(DrawConst.BrushSizeEnum.SMALL)) {
                    frame.smallSizeRadio.setSelected(true);
                } else if (sizeEnum.equals(DrawConst.BrushSizeEnum.MIDDLE)) {
                    frame.middleSizeRadio.setSelected(true);
                } else if (sizeEnum.equals(DrawConst.BrushSizeEnum.BIG)) {
                    frame.bigSizeRadio.setSelected(true);
                } else {
                    sizeGroup.clearSelection();
                }
                frame.paintConfig.setSize(sizeEnum);
            }

        });

        return this;
    }

    @Override
    public MainFrameBuilder initColorSelectData() {
//        frame.redColor.setIcon(new ColorIcon(Color.red, this.redColor.getWidth() - 4, this.redColor.getHeight() - 4));
//        frame.blueColor.setIcon(new ColorIcon(Color.blue, this.blueColor.getWidth() - 4, this.blueColor.getHeight() - 4));
//        frame.blackColor.setIcon(new ColorIcon(Color.black, this.blackColor.getWidth() - 4, this.blackColor.getHeight() - 4));

        redColor.addActionListener(e -> {
            frame.colorBtn.setBackground(Color.red);
            frame.colorBtn.setForeground(ColorUtils.contrastColor(Color.red));
            frame.paintConfig.setColor(Color.red);
        });

        blueColor.addActionListener(e -> {
            frame.colorBtn.setBackground(Color.blue);
            frame.colorBtn.setForeground(ColorUtils.contrastColor(Color.blue));
            frame.paintConfig.setColor(Color.blue);

        });

        blackColor.addActionListener(e -> {
            frame.colorBtn.setBackground(Color.black);
            frame.colorBtn.setForeground(ColorUtils.contrastColor(Color.black));
            frame.paintConfig.setColor(Color.black);
        });

        colorBtn.addActionListener(e -> {
            Color color = JColorChooser.showDialog(frame, "调色板", Color.RED);
            if (color != null) {
                // * 处理底部快捷按钮
                if (color.equals(Color.red)) {
                    frame.redColor.setSelected(true);
                } else if (color.equals(Color.black)) {
                    frame.blackColor.setSelected(true);
                } else if (color.equals(Color.blue)) {
                    frame.blueColor.setSelected(true);
                }
                // * 处理按钮色
                frame.colorBtn.setBackground(color);
                frame.colorBtn.setForeground(ColorUtils.contrastColor(color));
                // * 同步画笔配置
                frame.paintConfig.setColor(color);
            }
        });


        return this;
    }


    @Override
    public MainFrameBuilder initBrushStyleData() {
        frame.maoPaint.addActionListener(e -> frame.paintConfig.setStyle(DrawConst.BrushStyleEnum.PEN_MAO));

        frame.laPaint.addActionListener(e -> frame.paintConfig.setStyle(DrawConst.BrushStyleEnum.PEN_LA));

        frame.qianPaint.addActionListener(e -> frame.paintConfig.setStyle(DrawConst.BrushStyleEnum.PEN_QIAN));


        frame.rectBtn.addActionListener(e -> frame.paintConfig.setStyle(DrawConst.BrushStyleEnum.RECT));

        frame.rectFillBtn.addActionListener(e -> frame.paintConfig.setStyle(DrawConst.BrushStyleEnum.RECT_FILL));

        frame.circleBtn.addActionListener(e -> frame.paintConfig.setStyle(DrawConst.BrushStyleEnum.CIRCLE));

        frame.circleFillBtn.addActionListener(e -> frame.paintConfig.setStyle(DrawConst.BrushStyleEnum.CIRCLE_FILL));

        frame.lineBtn.addActionListener(e -> frame.paintConfig.setStyle(DrawConst.BrushStyleEnum.LINE));

        frame.lineDotBtn.addActionListener(e -> frame.paintConfig.setStyle(DrawConst.BrushStyleEnum.LINE_DOT));

        frame.roundBtn.addActionListener(e -> frame.paintConfig.setStyle(DrawConst.BrushStyleEnum.ROUND));

        frame.roundFillBtn.addActionListener(e -> frame.paintConfig.setStyle(DrawConst.BrushStyleEnum.ROUND_FILL));

        frame.insertTextBtn.addActionListener(e -> frame.paintConfig.setStyle(DrawConst.BrushStyleEnum.WORD));

        return this;
    }

    @Override
    public MainFrameBuilder initEraseData() {
        frame.eraseBtn.addActionListener(e -> frame.paintConfig.setStyle(DrawConst.BrushStyleEnum.ERASE));

        return this;
    }

    @Override
    public MainFrameBuilder initCanvas() {
        // * 为canvas设置UI
        frame.canvas = MyCanvas.getInstance();
        Dimension dimension = new Dimension(drawPanel.getWidth(), drawPanel.getHeight());
        frame.canvas.setPreferredSize(dimension);
        frame.canvas.setMinimumSize(dimension);
        frame.canvas.setMaximumSize(dimension);
        frame.canvas.setSize(dimension);
        frame.canvas.setLayout(null);
        frame.canvas.setOpaque(true);

        // * 为canvas添加监听器
        frame.canvas.addMouseListener(DrawListener.getInstance());
        frame.canvas.addMouseMotionListener(DrawListener.getInstance());

        // ((MyCanvas) this.canvas).setImage(new BufferedImage(this.canvas.getWidth(),this.canvas.getHeight(),BufferedImage.TYPE_INT_RGB));
        // ((MyCanvas) this.canvas).reset();
        // 外观模式优化
        CanvasUtils.reset();

        frame.drawPanel.add(canvas);

        return this;
    }

    @Override
    public void start() {
        // * 显示窗口
        frame.setVisible(true);
        // * 将canvas的画笔添加给统一配置管理器，在显示之后才能获取画笔
        frame.paintConfig.initGraphics(((MyCanvas) frame.canvas).getImage().getGraphics());
    }

    @Override
    public MainFrameBuilder activateFrameConfig() {
        // * 主题设置,使用了策略+工厂
//        new ThemeHandler(new DefaultThemeStrategy()).activateTheme(DrawConst.ThemeEnum.DEFAULT_LIGHT, this);
        // * 添加 应用图标
//        frame.setIconImages(FlatSVGUtils.createWindowIconImages("/FlatLaf.svg"));
        // * 禁止 最大化
        frame.setResizable(false);
        // * 使能 关闭按钮
        // TODO 关闭应该先提示保存，要弹窗，要断链接，没时间了，不做了
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // * 合适 布局
        frame.pack();

        return this;
    }

    @Override
    public MainFrameBuilder initThemeMenuData() {
        List<String> remarks = DrawConst.ThemeEnum.getRemarks();
        // TODO 切换主题会导致按钮大小变化，按钮宽度打出来有10+的浮动,这是主题的BUG
        remarks.forEach(remark -> {
            JMenuItem item = new JMenuItem(remark);
            item.addActionListener(e -> {
                String command = e.getActionCommand();
                DrawConst.ThemeEnum theme = DrawConst.ThemeEnum.getByRemark(command);
                assert theme != null;
//                new ThemeHandler(DrawConst.ThemeEnum
//                        .isDefaultTheme(theme)
//                        ? new DefaultThemeStrategy()
//                        : new ImportThemeStrategy())
//                        .activateTheme(theme, frame);
            });
            themeMenu.add(item);
        });

        return this;
    }

    @Override
    public MainFrameBuilder initImportMenuData() {
        importMenu.add(new BackgroundImportFactory().createItem());
        importMenu.add(new FileImportFactory().createItem());

        // TODO 关于按钮没实现，将其隐藏
        about.setVisible(false);
        return this;
    }

    @Override
    public MainFrameBuilder initOutportMenuData() {
        outputMenu.add(new JPGOutputFactory().createItem());
        outputMenu.add(new PNGOutputFactory().createItem());
        outputMenu.add(new FileOutputFactory().createItem());
        return this;
    }

    @Override
    public MainFrameBuilder initInsertTextData() {
        wordText.getDocument().addDocumentListener(new DocumentListener() {
            private void changeFilter(DocumentEvent event) {
                javax.swing.text.Document document = event.getDocument();
                try {
                    String text = document.getText(0, document.getLength());
                    paintConfig.setWordText(text);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                changeFilter(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeFilter(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeFilter(e);
            }
        });
        return this;
    }

    @Override
    public MainFrameBuilder initRecoveryBtnData() {
        Command shapeCommand = new ShapeCommand();
        redoBtn.addActionListener(e -> shapeCommand.redo(true));
        undoBtn.addActionListener(e -> shapeCommand.undo(true));
        return this;
    }

    @Override
    public MainFrameBuilder activateDefaultPaintConfig() {
        // 默认设置 红色按钮
        redColor.doClick();
        // 默认设置 尺寸中
        middleSizeRadio.doClick();
        // 默认设置 铅笔随手写
        qianPaint.doClick();
        return this;
    }


    @Override
    public MainFrameBuilder initButtonGroup() {
        sizeGroup.add(this.smallSizeRadio);
        sizeGroup.add(this.bigSizeRadio);
        sizeGroup.add(this.middleSizeRadio);

        colorGroup.add(this.redColor);
        colorGroup.add(this.blueColor);
        colorGroup.add(this.blackColor);

        styleGroup.add(this.maoPaint);
        styleGroup.add(this.laPaint);
        styleGroup.add(this.qianPaint);

        styleGroup.add(this.eraseBtn);

        styleGroup.add(this.rectBtn);
        styleGroup.add(this.rectFillBtn);
        styleGroup.add(this.circleBtn);
        styleGroup.add(this.circleFillBtn);
        styleGroup.add(this.lineBtn);
        styleGroup.add(this.lineDotBtn);
        styleGroup.add(this.roundBtn);
        styleGroup.add(this.roundFillBtn);

        styleGroup.add(this.insertTextBtn);

        return this;
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar menuBar;
    private JMenu importMenu;
    private JMenu outputMenu;
    private JMenu themeMenu;
    private JMenu about;
    private JPanel toolPanel;
    private JToggleButton eraseBtn;
    private JComboBox sizeBox;
    private JButton colorBtn;
    private JTextField wordText;
    private JToggleButton insertTextBtn;
    private JToggleButton rectBtn;
    private JToggleButton circleBtn;
    private JToggleButton lineBtn;
    private JToggleButton roundBtn;
    private JButton redoBtn;
    private JCheckBox smallSizeRadio;
    private JCheckBox middleSizeRadio;
    private JCheckBox bigSizeRadio;
    private JToggleButton redColor;
    private JToggleButton blueColor;
    private JToggleButton blackColor;
    private JToggleButton maoPaint;
    private JToggleButton laPaint;
    private JToggleButton qianPaint;
    private JToggleButton rectFillBtn;
    private JToggleButton circleFillBtn;
    private JToggleButton lineDotBtn;
    private JToggleButton roundFillBtn;
    private JButton undoBtn;
    private JPanel drawPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on


    @Override
    public void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        menuBar = new JMenuBar();
        importMenu = new JMenu();
        outputMenu = new JMenu();
        themeMenu = new JMenu();
        about = new JMenu();
        toolPanel = new JPanel();
        eraseBtn = new JToggleButton();
        sizeBox = new JComboBox();
        colorBtn = new JButton();
        wordText = new JTextField();
        insertTextBtn = new JToggleButton();
        rectBtn = new JToggleButton();
        circleBtn = new JToggleButton();
        lineBtn = new JToggleButton();
        roundBtn = new JToggleButton();
        redoBtn = new JButton();
        smallSizeRadio = new JCheckBox();
        middleSizeRadio = new JCheckBox();
        bigSizeRadio = new JCheckBox();
        redColor = new JToggleButton();
        blueColor = new JToggleButton();
        blackColor = new JToggleButton();
        maoPaint = new JToggleButton();
        laPaint = new JToggleButton();
        qianPaint = new JToggleButton();
        rectFillBtn = new JToggleButton();
        circleFillBtn = new JToggleButton();
        lineDotBtn = new JToggleButton();
        roundFillBtn = new JToggleButton();
        undoBtn = new JButton();
        drawPanel = new JPanel();

        //======== this ========
        setTitle("画板");
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[fill]",
                // rows
                "[]" +
                        "[]"));

        //======== menuBar ========
        {

            //======== importMenu ========
            {
                importMenu.setText("导入");
            }
            menuBar.add(importMenu);

            //======== outputMenu ========
            {
                outputMenu.setText("导出");
            }
            menuBar.add(outputMenu);

            //======== themeMenu ========
//            {
//                themeMenu.setText("主题");
//            }
//            menuBar.add(themeMenu);

            //======== about ========
//            {
//                about.setText("关于");
//            }
//            menuBar.add(about);
        }
        setJMenuBar(menuBar);

        //======== toolPanel ========
        {
            toolPanel.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[fill]" +
                            "[fill]" +
                            "[fill]" +
                            "[fill]" +
                            "[fill]" +
                            "[fill]" +
                            "[fill]" +
                            "[fill]" +
                            "[fill]" +
                            "[fill]" +
                            "[fill]" +
                            "[fill]" +
                            "[fill]",
                    // rows
                    "[]" +
                            "[]"));

            //---- eraseBtn ----
            eraseBtn.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
            eraseBtn.setMaximumSize(new Dimension(78, 78));
            eraseBtn.setMinimumSize(new Dimension(78, 78));
            eraseBtn.setPreferredSize(new Dimension(78, 78));
            eraseBtn.setText("擦 除");
            toolPanel.add(eraseBtn, "cell 0 0 1 2");
            toolPanel.add(sizeBox, "cell 1 0");

            //---- colorBtn ----
            colorBtn.setText("调色板");
            colorBtn.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
            colorBtn.setMaximumSize(new Dimension(110, 30));
            colorBtn.setMinimumSize(new Dimension(110, 30));
            colorBtn.setPreferredSize(new Dimension(110, 30));
            toolPanel.add(colorBtn, "cell 2 0 3 1");
            toolPanel.add(wordText, "cell 5 0 2 1");

            //---- insertTextBtn ----
            insertTextBtn.setText("插入文字");
            insertTextBtn.setMinimumSize(new Dimension(100, 30));
            insertTextBtn.setPreferredSize(new Dimension(100, 30));
            insertTextBtn.setMaximumSize(new Dimension(100, 30));
            toolPanel.add(insertTextBtn, "cell 7 0");

            //---- rectBtn ----
            rectBtn.setText("矩形");
            rectBtn.setMaximumSize(new Dimension(100, 30));
            rectBtn.setMinimumSize(new Dimension(100, 30));
            rectBtn.setPreferredSize(new Dimension(100, 30));
            toolPanel.add(rectBtn, "cell 8 0");

            //---- circleBtn ----
            circleBtn.setText("圆形");
            circleBtn.setMaximumSize(new Dimension(100, 30));
            circleBtn.setMinimumSize(new Dimension(100, 30));
            circleBtn.setPreferredSize(new Dimension(100, 30));
            toolPanel.add(circleBtn, "cell 9 0");

            //---- lineBtn ----
            lineBtn.setText("直线");
            lineBtn.setMaximumSize(new Dimension(100, 30));
            lineBtn.setMinimumSize(new Dimension(100, 30));
            lineBtn.setPreferredSize(new Dimension(100, 30));
            toolPanel.add(lineBtn, "cell 10 0");

            //---- roundBtn ----
            roundBtn.setText("圆角矩形");
            roundBtn.setMaximumSize(new Dimension(100, 30));
            roundBtn.setMinimumSize(new Dimension(100, 30));
            roundBtn.setPreferredSize(new Dimension(100, 30));
            toolPanel.add(roundBtn, "cell 11 0");

            //---- redoBtn ----
            redoBtn.setText("恢复");
            redoBtn.setPreferredSize(new Dimension(95, 30));
            redoBtn.setMinimumSize(new Dimension(95, 30));
            redoBtn.setMaximumSize(new Dimension(95, 30));
            toolPanel.add(redoBtn, "cell 12 0");

            //---- smallSizeRadio ----
            smallSizeRadio.setText("细");
            smallSizeRadio.setMaximumSize(new Dimension(60, 21));
            smallSizeRadio.setMinimumSize(new Dimension(60, 21));
            smallSizeRadio.setPreferredSize(new Dimension(60, 19));
            toolPanel.add(smallSizeRadio, "cell 1 1");

            //---- middleSizeRadio ----
            middleSizeRadio.setText("中");
            middleSizeRadio.setMaximumSize(new Dimension(60, 21));
            middleSizeRadio.setMinimumSize(new Dimension(60, 21));
            middleSizeRadio.setPreferredSize(new Dimension(60, 21));
            toolPanel.add(middleSizeRadio, "cell 1 1");

            //---- bigSizeRadio ----
            bigSizeRadio.setText("粗");
            bigSizeRadio.setPreferredSize(new Dimension(60, 21));
            bigSizeRadio.setMaximumSize(new Dimension(60, 21));
            bigSizeRadio.setMinimumSize(new Dimension(60, 21));
            bigSizeRadio.setFont(bigSizeRadio.getFont().deriveFont(bigSizeRadio.getFont().getStyle() & ~Font.BOLD));
            toolPanel.add(bigSizeRadio, "cell 1 1");

            //---- redColor ----
            redColor.setMinimumSize(new Dimension(30, 30));
            redColor.setMaximumSize(new Dimension(30, 30));
            redColor.setPreferredSize(new Dimension(30, 30));
            redColor.setBackground(Color.RED);
            toolPanel.add(redColor, "cell 2 1");

            //---- blueColor ----
            blueColor.setMinimumSize(new Dimension(30, 30));
            blueColor.setMaximumSize(new Dimension(30, 30));
            blueColor.setPreferredSize(new Dimension(30, 30));
            blueColor.setBackground(Color.BLUE);
            toolPanel.add(blueColor, "cell 3 1");

            //---- blackColor ----
            blackColor.setMinimumSize(new Dimension(30, 30));
            blackColor.setMaximumSize(new Dimension(30, 30));
            blackColor.setPreferredSize(new Dimension(30, 30));
            blackColor.setBackground(Color.BLACK);
            toolPanel.add(blackColor, "cell 4 1");

            //---- maoPaint ----
            maoPaint.setText("毛笔");
            maoPaint.setMinimumSize(new Dimension(85, 30));
            maoPaint.setMaximumSize(new Dimension(85, 30));
            maoPaint.setPreferredSize(new Dimension(85, 30));
            toolPanel.add(maoPaint, "cell 5 1");

            //---- laPaint ----
            laPaint.setText("蜡笔");
            laPaint.setMinimumSize(new Dimension(85, 30));
            laPaint.setMaximumSize(new Dimension(85, 30));
            laPaint.setPreferredSize(new Dimension(85, 30));
            toolPanel.add(laPaint, "cell 6 1");

            //---- qianPaint ----
            qianPaint.setText("铅笔");
            qianPaint.setMinimumSize(new Dimension(100, 30));
            qianPaint.setMaximumSize(new Dimension(100, 30));
            qianPaint.setPreferredSize(new Dimension(100, 30));
            toolPanel.add(qianPaint, "cell 7 1");

            //---- rectFillBtn ----
            rectFillBtn.setText("填充矩形");
            rectFillBtn.setMaximumSize(new Dimension(100, 30));
            rectFillBtn.setMinimumSize(new Dimension(100, 30));
            rectFillBtn.setPreferredSize(new Dimension(100, 30));
            toolPanel.add(rectFillBtn, "cell 8 1");

            //---- circleFillBtn ----
            circleFillBtn.setText("填充圆形");
            circleFillBtn.setMaximumSize(new Dimension(100, 30));
            circleFillBtn.setMinimumSize(new Dimension(100, 30));
            circleFillBtn.setPreferredSize(new Dimension(100, 30));
            toolPanel.add(circleFillBtn, "cell 9 1");

//            //---- lineDotBtn ----
//            lineDotBtn.setText("\u5750\u6807\u7cfb");
//            lineDotBtn.setMaximumSize(new Dimension(100, 30));
//            lineDotBtn.setMinimumSize(new Dimension(100, 30));
//            lineDotBtn.setPreferredSize(new Dimension(100, 30));
//            toolPanel.add(lineDotBtn, "cell 10 1");

            //---- roundFillBtn ----
            roundFillBtn.setText("圆角填充");
            roundFillBtn.setMaximumSize(new Dimension(100, 30));
            roundFillBtn.setMinimumSize(new Dimension(100, 30));
            roundFillBtn.setPreferredSize(new Dimension(100, 30));
            toolPanel.add(roundFillBtn, "cell 11 1");

            //---- undoBtn ----
            undoBtn.setText("撤销");
            undoBtn.setPreferredSize(new Dimension(95, 30));
            undoBtn.setMaximumSize(new Dimension(95, 30));
            undoBtn.setMinimumSize(new Dimension(95, 30));
            toolPanel.add(undoBtn, "cell 12 1");
        }
        contentPane.add(toolPanel, "cell 0 0");

        //======== drawPanel ========
        {
            drawPanel.setMinimumSize(new Dimension(1220, 700));
            drawPanel.setMaximumSize(new Dimension(1220, 700));
            drawPanel.setPreferredSize(new Dimension(1220, 700));
            drawPanel.setLayout(new GridLayout(1, 1));
        }
        contentPane.add(drawPanel, "cell 0 1 1 4");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
}
