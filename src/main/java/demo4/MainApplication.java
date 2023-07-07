package demo4;

import demo4.recovery.Caretaker;
import demo4.recovery.CaretakerProxy;
import demo4.recovery.ICaretaker;
import demo4.ui.view.MainFrame;
import demo4.ui.view.MainFrameDirector;

public class MainApplication {
    public void init(){
        MainFrame frame = (MainFrame) MainFrameDirector.create(new MainFrame());
        ICaretaker proxy = (ICaretaker)new CaretakerProxy(new Caretaker()).getProxy();
        proxy.recovery();
    }

    public static void main(String[] args) {
        new MainApplication().init();
    }
}
