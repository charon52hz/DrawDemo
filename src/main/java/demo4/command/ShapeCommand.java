package demo4.command;

import demo4.ui.canvas.MyCanvas;
import demo4.ui.component.pen.PenShape;
import demo4.ui.component.shape.AbstractShape;
import demo4.utils.CanvasUtils;

import java.awt.*;
import java.util.List;

public class ShapeCommand implements Command {
    private final MyCanvas canvas = MyCanvas.getInstance();
    List<AbstractShape> shapes = null;
//    List<AbstractShape> history = canvas.getHistory();
    @Override
    public void execute(AbstractShape shape, Boolean isShow) {
        canvas.getShapes().add(shape);
        if (isShow) {
            shape.show(CanvasUtils.getGraphicsAfterReset());
        }
    }

    @Override
    public void undo(Boolean isShow) {
        // 如果是三个画笔+橡皮需要撤销，需要一下全撤掉，使用PenShape做分割
        shapes = canvas.getShapes();
        //AbstractShape remove = null;
        int limit = shapes.size() - 1;
        if (limit >= 0) {
            // 是PenShape结尾的
            if (shapes.get(limit) instanceof PenShape) {
                // 删掉分隔符
//                remove = shapes.remove(limit);
//                history.add(remove);
                shapes.remove(limit);
                limit--;
                // 是笔迹
                if (limit >= 0
                        && shapes.get(limit) instanceof PenShape
                        && !shapes.get(limit).getClass().equals(PenShape.class)) {
                    for (int i = limit; i >= 0; i--) {
                        // 只要是笔迹就删除
                        if (shapes.get(i) instanceof PenShape && !shapes.get(i).getClass().equals(PenShape.class)) {
//                            remove = shapes.remove(i);
//                            history.add(remove);
                            shapes.remove(i);
                        }

                        else {
                            break;
                        }
                    }
                }

            } else {
//                remove = shapes.remove(limit);
//                history.add(remove);
                shapes.remove(limit);
            }
        }
        if (isShow) {
            CanvasUtils.reset().addImage().redraw().show();
        }
    }

    @Override
    public void redo(Boolean isShow) {
        //恢复笔迹难以实现，会出现OOM问题，这里以清空代替
        canvas.getShapes().clear();
//        if (!(history ==null) || !history.isEmpty()){
//            if (!(history.get(history.size()-1) instanceof PenShape)){
//                canvas.getShapes().add(history.get(history.size()-1));
//            }else {
//                while (!history.isEmpty() && history.get(history.size() - 1) instanceof PenShape
//                        && !history.get(history.size() - 1).getClass().equals(PenShape.class)) {
//                    canvas.getShapes().add(history.get(history.size() - 1));
//                }
//            }
//        }else{
//            System.out.println("已到最新状态");
//        }
        if (isShow) {
            CanvasUtils.reset().removeImage().redraw().show();
        }
    }
}
