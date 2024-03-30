package common.model;

import java.io.Serializable;
import java.util.Objects;

public class Coordinates implements Serializable {
    private final Integer x; //Поле не может быть null
    private final long y;

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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Coordinates that = (Coordinates) object;
        return y == that.y && Objects.equals(x, that.x);
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
