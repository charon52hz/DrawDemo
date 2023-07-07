package demo4.ui.component.pair;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

public class Point implements IPair, Serializable {
    private final static long serialVersionUID = 1L;


    private Pair points[];

    public Point(int size) {
        points = new Pair[size];
        for (int i = 0; i < size; i++) {
            points[i] = new Pair();
        }
    }

    @Override
    public Iterator createIterator() {
        return  new PairIterator();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class Pair implements Serializable {
        private final static long serialVersionUID = 1L;

        int x;
        int y;
    }


    public class PairIterator implements Iterator<Pair> {
        int index;

        @Override
        public boolean hasNext() {
            return index < points.length;
        }

        @Override
        public int getIndex() {
            return index;
        }

        @Override
        public Pair next() {
            if (this.hasNext()) {
                return points[index++];
            }
            return null;
        }
    }

}
