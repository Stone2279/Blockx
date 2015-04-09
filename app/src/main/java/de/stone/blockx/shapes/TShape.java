package de.stone.blockx.shapes;

public class TShape extends Shape {

    public TShape() {
        states.add(new int[][]{{0, 6, 0}, {6, 6, 6}});
        states.add(new int[][]{{0, 6}, {6, 6}, {0, 6}});
        states.add(new int[][]{{6, 6, 6}, {0, 6, 0}});
        states.add(new int[][]{{6, 0}, {6, 6}, {6, 0}});
    }

}
