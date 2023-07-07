package demo2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class PaintApp extends JFrame {
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;

    private String currentTool;
    private Color currentColor;
    private int currentSize;

    private BufferedImage canvas;
    private Graphics2D g;

    private JButton colorButton;
    private JButton sizeButton;
    private JButton saveButton;
    private JButton openButton;
    private JButton undoButton;
    private JButton redoButton;

    private JPanel canvasPanel;
    private boolean isDrawing;
    private int startX, startY, endX, endY;

    public PaintApp() {
        setTitle("Paint App");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 初始化工具
        currentTool = "Pen";
        currentColor = Color.BLACK;
        currentSize = 5;

        // 初始化画布
        canvas = new BufferedImage(DEFAULT_WIDTH, DEFAULT_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g = canvas.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        g.setColor(currentColor);
        g.setStroke(new BasicStroke(currentSize));

        // 初始化按钮
        colorButton = new JButton("选择颜色");
        sizeButton = new JButton("选择粗细");
        saveButton = new JButton("保存为文件");
        openButton = new JButton("打开文件");
        undoButton = new JButton("撤销");
        redoButton = new JButton("重做");

        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(PaintApp.this, "选择颜色", currentColor);
                if (newColor != null) {
                    currentColor = newColor;
                    g.setColor(currentColor);
                }
            }
        });

        sizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newSizeString = JOptionPane.showInputDialog(PaintApp.this, "选择粗细", currentSize);
                if (newSizeString != null) {
                    try {
                        int newSize = Integer.parseInt(newSizeString);
                        currentSize = newSize;
                        g.setStroke(new BasicStroke(currentSize));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(PaintApp.this, "请输入有效的粗细值");
                    }
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File outputFile = new File("drawing.dat");
                    FileOutputStream fos = new FileOutputStream(outputFile);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(canvas);
                    oos.close();
                    JOptionPane.showMessageDialog(PaintApp.this, "文件保存成功");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PaintApp.this, "文件保存失败");
                }
            }
        });

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File inputFile = new File("drawing.dat");
                    FileInputStream fis = new FileInputStream(inputFile);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    canvas = (BufferedImage) ois.readObject();
                    ois.close();
                    g = canvas.createGraphics();
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g.setColor(currentColor);
                    g.setStroke(new BasicStroke(currentSize));
                    canvasPanel.repaint();
                    JOptionPane.showMessageDialog(PaintApp.this, "文件打开成功");
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PaintApp.this, "文件打开失败");
                }
            }
        });

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 实现撤销功能
            }
        });

        redoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 实现重做功能
            }
        });

        // 初始化画布面板
        canvasPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(canvas, 0, 0, null);
                if (isDrawing) {
                    g.setColor(currentColor);
                    //g.setStroke(new BasicStroke(currentSize));
                    if (currentTool.equals("Pen")) {
                        g.drawLine(startX, startY, endX, endY);
                    } else if (currentTool.equals("Rectangle")) {
                        int x = Math.min(startX, endX);
                        int y = Math.min(startY, endY);
                        int width = Math.abs(endX - startX);
                        int height = Math.abs(endY - startY);
                        g.drawRect(x, y, width, height);
                    } else if (currentTool.equals("Ellipse")) {
                        int x = Math.min(startX, endX);
                        int y = Math.min(startY, endY);
                        int width = Math.abs(endX - startX);
                        int height = Math.abs(endY - startY);
                        g.drawOval(x, y, width, height);
                    }
                }
            }
        };

        canvasPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isDrawing = true;
                startX = e.getX();
                startY = e.getY();
                endX = startX;
                endY = startY;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isDrawing = false;
                if (currentTool.equals("Pen")) {
                    g.drawLine(startX, startY, endX, endY);
                } else if (currentTool.equals("Rectangle")) {
                    int x = Math.min(startX, endX);
                    int y = Math.min(startY, endY);
                    int width = Math.abs(endX - startX);
                    int height = Math.abs(endY - startY);
                    g.drawRect(x, y, width, height);
                } else if (currentTool.equals("Ellipse")) {
                    int x = Math.min(startX, endX);
                    int y = Math.min(startY, endY);
                    int width = Math.abs(endX - startX);
                    int height = Math.abs(endY - startY);
                    g.drawOval(x, y, width, height);
                }
                canvasPanel.repaint();
            }
        });

        canvasPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endX = e.getX();
                endY = e.getY();
                canvasPanel.repaint();
            }
        });

        // 添加按钮和画布面板到窗口
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(colorButton);
        buttonPanel.add(sizeButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(openButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(redoButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(canvasPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PaintApp app = new PaintApp();
                app.setVisible(true);
            }
        });
    }
}

