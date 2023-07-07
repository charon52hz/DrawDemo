package demo4.ui.trans.output;

import demo4.decorator.FileDecorator;
import demo4.decorator.FileOutput;
import demo4.ui.trans.TransFactory;

import javax.swing.*;

public class FileOutputFactory implements TransFactory {
    @Override
    public JMenuItem createItem() {
        FileOuputMenuItem item = new FileOuputMenuItem();

        item.addActionListener(e-> new FileDecorator(new FileOutput()).output());

        return item;
    }
}
