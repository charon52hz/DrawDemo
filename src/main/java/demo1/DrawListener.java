package demo1;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class DrawListener extends MouseAdapter implements ActionListener {
    private int x,y,x0,y0,x1,y1;
    private Graphics2D g;
    private JFrame jf;
    private String type = "line";
    private Color color = Color.black;
    private int flag = 1;

    public void setG(Graphics g1,JFrame jf){
        g = (Graphics2D) g1;
        this.jf = jf;
        //开启抗锯齿
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();//获取按钮上的文字
        if (ac.equals("")){
            JButton button = (JButton) e.getSource();
            color =  button.getBackground();
        }else {
            type = ac;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        x0 = e.getX();
        y0 = e.getY();
        g.setColor(color);      //设置画笔颜色
        g.setStroke(new BasicStroke(1));    //设置画笔粗细
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        x = e.getX();
        y = e.getY();
        //释放点比按压点小时，调换位置，防止不能画出图形的bug
        int temp;
        if (x<x0){
            temp = x0;
            x0 = x;
            x = temp;
        }
        if (y<y0){
            temp = y0;
            y0 = y;
            y = temp;
        }

        if (type.equals("直线")){
            g.drawLine(x,y,x0,y0);
        }else if (type.equals("三角形")){
            g.drawLine(x,y,x0,y0);
            g.drawLine(x,y, x0, y);
            g.drawLine(x0, y0, x0, y);
        }else if (type.equals("矩形")){
            g.drawRect(x0,y0,x-x0,y-y0);
        }else if (type.equals("圆形")){
            g.drawOval(x0,y0,x-x0,y-y0);
        }else if (type.equals("填充矩形")){
            g.fillRect(x0,y0,x-x0,y-y0);
        }else if (type.equals("填充圆形")){
            g.fillOval(x0,y0,x-x0,y-y0);
        }else if (type.equals("导入图片")){
//            g.fillRect(x0,y0,x-x0,y-y0);
//            Image image = new ImageIcon().getImage();
//            g.drawImage(image,x0,y0,x-x0,y-y0,null);
            g.fillRect(x0, y0, x - x0, y - y0);
            try {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    Image image = ImageIO.read(selectedFile);
                    g.drawImage(image, x0, y0, x - x0, y - y0, null);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        if (type.equals("喷漆")){
            x1 = e.getX();
            y1 = e.getY();
            g.fillOval(x1,y1,2,2);
            g.fillOval(x1+10,y1+10,2,2);
            g.fillOval(x1-10,y1-10,3,3);
            g.fillOval(x1+10,y1-10,5,5);
            g.fillOval(x1-10,y1+10,1,1);
            g.fillOval(x1+4,y1-3,5,5);
        }else if (type.equals("橡皮擦")){
            x1 = e.getX();
            y1 = e.getY();
            g.setColor(jf.getContentPane().getBackground());
            g.setStroke(new BasicStroke(50));
            g.drawLine(x0,y0,x1,y1);
            x0 = x1;
            y0 = y1;
        }else if (type.equals("毛笔")){
            x1 = e.getX();
            y1 = e.getY();
            g.setStroke(new BasicStroke(8));
            g.drawLine(x0,y0,x1,y1);
            x0 = x1;
            y0 = y1;
        }
    }
}
