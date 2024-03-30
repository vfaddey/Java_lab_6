package common.model;

import java.io.Serializable;
import java.util.Objects;

public class Location implements Serializable {

    private final double x;
    private final double y;
    private final long z;

    public Location(double x, double y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public long getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Location location = (Location) object;
        return Double.compare(x, location.x) == 0 && Double.compare(y, location.y) == 0 && z == location.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
