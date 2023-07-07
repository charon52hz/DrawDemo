package demo4.recovery;

import demo4.ui.component.shape.AbstractShape;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Memento implements Serializable {
    private final static long serialVersionUID = 1L;

    // TODO 背景图片暂时无法通过备忘录保存

    // 静态变量优化存储空间
    private List<AbstractShape> shapes = null;

    //这样处理备忘录会有内存泄漏，效率等问题，但先这样写
    public Memento(List<AbstractShape> shapes) {
        this.shapes = new ArrayList<>();
        this.shapes.addAll(shapes);
    }
}
