package de.stone.blockx.shapes;

public class ShapeFactory {
    private final static Shape[] SHAPES = new Shape[]{new IShape(), new JShape(), new LShape(), new OShape(), new SShape(), new TShape(),
            new ZShape()};

    private Shape next;

    public ShapeFactory() {
        next = getRandomShape();
    }

    public Shape getShape() {
        final Shape copy = new Shape(next);
        next = getRandomShape();

        return copy;
    }

    public Shape nextShapePreview() {
        return next;
    }

    private Shape getRandomShape() {
        return SHAPES[(int) (Math.random() * 7.0)];
    }
}
