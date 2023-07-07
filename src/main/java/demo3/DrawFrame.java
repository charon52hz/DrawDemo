package demo3;

import demo2.PaintApp;
import demo3.DrawListener;
import demo3.Shape.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Stack;

public class DrawFrame extends JFrame {
    private Stack<Shape> history;
    private JPanel canvasPanel;
    private JPanel buttonPanel;
    private JButton colorButton;
    private JButton sizeButton;
    private JButton saveButton;
    private JButton openButton;
    private JButton undoButton;
    private JButton redoButton;
    private JButton eraserButton;   //橡皮擦
    public void initUI(){
        this.setLayout(new FlowLayout());
        this.setTitle("画图");
        this.setSize(1150,700);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);

        history = new Stack<Shape>();

        DrawListener drawListener = new DrawListener();
        buttonPanel = new JPanel();
        canvasPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Shape shape:history){
                    shape.draw(g);
                }
            }
        };

        String[] typeArray = {"直线","三角形","矩形","填充矩形","圆形","填充圆形","填充图片","喷漆","毛笔","铅笔"};
        for (int i=0; i<typeArray.length; i++){
            JButton button = new JButton(typeArray[i]);
            button.addActionListener(drawListener);
            buttonPanel.add(button);
        }
//        String[] penArray = {"喷漆","毛笔","铅笔"};
//        for (int i=0;i<penArray.length;i++){
//            JButton button = new JButton(penArray[i]);
//            button.addActionListener(drawListener);
//            this.add(button);
//        }
        Color[] colors = {Color.BLACK,Color.WHITE,Color.YELLOW,Color.BLUE,Color.RED,Color.GREEN};
        for (int i =0;i<colors.length;i++) {
            JButton button = new JButton();
            button.setBackground(colors[i]);
            button.setPreferredSize(new Dimension(30,30));
            button.addActionListener(drawListener);
            this.add(button);
        }

        colorButton = new JButton("选择颜色");
        sizeButton = new JButton("选择粗细");
        saveButton = new JButton("保存为文件");
        openButton = new JButton("打开文件");
        undoButton = new JButton("撤销");
        redoButton = new JButton("重做");
        eraserButton = new JButton("橡皮擦");

        buttonPanel.add(colorButton);
        buttonPanel.add(sizeButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(openButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(redoButton);
        buttonPanel.add(eraserButton);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(buttonPanel, BorderLayout.NORTH);
        this.getContentPane().add(canvasPanel, BorderLayout.CENTER);

        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(DrawFrame.this, "选择颜色", drawListener.getColor());
                if (newColor != null) {
                    drawListener.setColor(newColor);
                }
            }
        });

        sizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newSizeString = JOptionPane.showInputDialog(DrawFrame.this, "选择粗细", drawListener.getSize());
                if (newSizeString != null) {
                    try {
                        int newSize = Integer.parseInt(newSizeString);
                        drawListener.setSize(newSize);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(DrawFrame.this, "请输入有效的粗细值");
                    }
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File outputFile = new File("drawing.png");
                    FileOutputStream fos = new FileOutputStream(outputFile);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(canvasPanel);
                    oos.close();
                    JOptionPane.showMessageDialog(DrawFrame.this, "文件保存成功");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(DrawFrame.this, "文件保存失败");
                }
            }
        });

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File inputFile = new File("drawing.png");
                    FileInputStream fis = new FileInputStream(inputFile);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    canvasPanel = (JPanel) ois.readObject();
                    ois.close();
                    Graphics2D g = (Graphics2D) canvasPanel.getGraphics();
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g.setColor(drawListener.getColor());
                    g.setStroke(new BasicStroke(drawListener.getSize()));
                    canvasPanel.repaint();
                    JOptionPane.showMessageDialog(DrawFrame.this, "文件打开成功");
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(DrawFrame.this, "文件打开失败");
                }
            }
        });

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!history.isEmpty()) {
                    history.pop(); // 弹出最后一个形状
                    canvasPanel.repaint(); // 重新绘制画布
                }
            }
        });



        this.setVisible(true);
        Graphics graphics = this.getGraphics();
        drawListener.setG(graphics,canvasPanel);
        this.addMouseListener(drawListener);
        this.addMouseMotionListener(drawListener);
    }

    public static void main(String[] args) {
        new DrawFrame().initUI();
    }
}
