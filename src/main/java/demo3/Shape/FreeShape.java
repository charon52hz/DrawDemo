package demo3.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class FreeShape extends Shape implements Iterator {
    private ArrayList<Point> points = new ArrayList<Point>(); // 存储坐标点的列表
    private int index;

    public void addPoint(Point point){
        points.add(point);
    }

    public FreeShape() {
    }

    @Override
    public void draw(Graphics g) {
        for (Point p1 : points) {
            Point p2 = points.iterator().next();
            g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }
    }

    @Override
    public boolean hasNext() {
        return index<points.size();
    }

    @Override
    public Object next() {
        if (this.hasNext()){
            return points.get(index++);
        }
        return null;
    }
}
