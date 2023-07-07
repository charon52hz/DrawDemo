package demo4.ui.trans.output;

import demo4.decorator.JpgDecorator;
import demo4.decorator.JpgOutput;
import demo4.ui.trans.TransFactory;

import javax.swing.*;

public class JPGOutputFactory implements TransFactory {
    @Override
    public JMenuItem createItem() {
        JPGMenuItem item = new JPGMenuItem();

        item.addActionListener(e-> new JpgDecorator(new JpgOutput()).output());

        return item;
    }
}
