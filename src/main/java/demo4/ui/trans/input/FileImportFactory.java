package demo4.ui.trans.input;

import demo4.decorator.FileDecorator;
import demo4.decorator.FileImport;
import demo4.ui.trans.TransFactory;

import javax.swing.*;

public class FileImportFactory implements TransFactory {
    @Override
    public JMenuItem createItem() {
        FileImportMenuItem item = new FileImportMenuItem();

        item.addActionListener(e-> new FileDecorator(new FileImport()).input());

        return item;
    }
}
