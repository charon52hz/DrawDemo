package demo1;

import javax.swing.*;
import java.awt.*;

public class DrawFrame extends JFrame {
    public void initUI(){
        this.setLayout(new FlowLayout());
        this.setTitle("画图");
        this.setSize(1150,700);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);

        DrawListener drawListener = new DrawListener();
        String[] typeArray = {"保存","另存为","导入图片","选择粗细","选择颜色","直线","三角形","矩形","填充矩形","圆形","填充圆形","喷漆","毛笔","橡皮擦","填充图片","撤销","恢复"};
        for (int i=0;i<typeArray.length;i++){
            JButton button = new JButton(typeArray[i]);
            button.addActionListener(drawListener);
            this.add(button);
        }
        Color[] colors = {Color.BLACK,Color.WHITE,Color.YELLOW,Color.BLUE,Color.RED,Color.GREEN};
        for (int i =0;i<colors.length;i++) {
            JButton button = new JButton();
            button.setBackground(colors[i]);
            button.setPreferredSize(new Dimension(30,30));
            button.addActionListener(drawListener);
            this.add(button);
        }
        this.setVisible(true);
        Graphics graphics = this.getGraphics();
        drawListener.setG(graphics,this);
        this.addMouseListener(drawListener);
        this.addMouseMotionListener(drawListener);
    }

    public static void main(String[] args) {
        new DrawFrame().initUI();
    }
}
