package demo4.ui.component.pair;

public interface Iterator<T>  {
    /**
     * 移动到聚合对象中下一个元素
     */
    T next();
    /**
     * 判断是否已经移动到聚合对象的最后一个元素
     */
    boolean hasNext();

    /**
     * 获取当前下标
     */
    int getIndex();
}
