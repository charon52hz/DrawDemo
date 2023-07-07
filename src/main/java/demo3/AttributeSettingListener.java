package demo3;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Getter
public class AttributeSettingListener extends MouseAdapter implements ActionListener {

    private Graphics2D g;
    private String type = "铅笔";
    private Color color = Color.black;
    private int size = 1;   //画笔的粗细

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
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (type.equals("铅笔")){
            g.setStroke(new BasicStroke(size));
            g.setColor(color);
        }
    }
}
