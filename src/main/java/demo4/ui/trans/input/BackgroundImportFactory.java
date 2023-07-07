package demo4.ui.trans.input;

import demo4.decorator.BackgroundDecorator;
import demo4.decorator.BackgroundImport;
import demo4.ui.trans.TransFactory;

import javax.swing.*;

public class BackgroundImportFactory implements TransFactory {
    @Override
    public JMenuItem createItem() {
        BackgroundMenuItem item = new BackgroundMenuItem();

        item.addActionListener(e -> new BackgroundDecorator(new BackgroundImport()).input());

        return item;
    }
}
