package demo4.ui.trans.output;

import demo4.decorator.PngDecorator;
import demo4.decorator.PngOutput;
import demo4.ui.trans.TransFactory;

import javax.swing.*;

public class PNGOutputFactory implements TransFactory {
    @Override
    public JMenuItem createItem() {
        PNGMenuItem item = new PNGMenuItem();

        item.addActionListener(e-> new PngDecorator(new PngOutput()).output());

        return item;
    }
}
