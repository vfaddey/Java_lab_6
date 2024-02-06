package model;

public class Coordinates {
    private Integer x; //Поле не может быть null
    private long y;

    public Coordinates(Integer x, long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Integer getX() {
        return x;
    }

    public long getY() {
        return y;
    }
}
