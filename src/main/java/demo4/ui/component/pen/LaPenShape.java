package demo4.ui.component.pen;

import demo4.ui.component.pair.Iterator;
import demo4.ui.component.pair.Point;
import lombok.Data;

import java.awt.*;
import java.util.Random;

@Data
public class LaPenShape extends PenShape {
    public LaPenShape() {
        super();
    }


    // private List<Pair<Integer,Integer>> points;
    private demo4.ui.component.pair.Point points = null;


    public LaPenShape(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
        Random rand = new Random();
        int size = 30 + (int) (this.getPaintConfig().getSize().getValue() / 2 * 10);
        int limit = 10 + (int) (this.getPaintConfig().getSize().getValue());
        // points = new ArrayList<>(size);
        points = new demo4.ui.component.pair.Point(size);
        for (demo4.ui.component.pair.Iterator iter = points.createIterator(); iter.hasNext(); ) {
            demo4.ui.component.pair.Point.Pair pair = (demo4.ui.component.pair.Point.Pair) iter.next();
            pair.setX(x2 + rand.nextInt(limit)).setY(y2 + rand.nextInt(limit));
        }
    }

    @Override
    public void specialSolveGraphics(Graphics graphics) {
        this.getPaintConfig().setGraphicsSize((float) (1 + 0.1 * this.getPaintConfig().getSize().getValue()));
    }

    @Override
    public void draw(Graphics graphics) {
        // for (int i = 0; i < this.points.size();i++) {
        //     graphics.drawLine(points.get(i).getKey(), points.get(i).getValue(), points.get(i).getKey(), points.get(i).getValue());
        // }
        for (Iterator iter = points.createIterator(); iter.hasNext(); ) {
            demo4.ui.component.pair.Point.Pair pair = (Point.Pair) iter.next();
            graphics.drawLine(pair.getX(), pair.getY(), pair.getX(), pair.getY());
        }
    }

}
