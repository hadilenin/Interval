import java.util.BitSet;
import java.util.Hashtable;
import java.util.Random;

import static java.lang.Math.*;

public class Interval implements Comparable,Cloneable {
    private int leftBound;
    private int rightBound;

    public Interval(int var1, int var2) {
        if (var1 <= var2) {
            this.leftBound = var1;
            this.rightBound = var2;
        } else {
            this.leftBound = var2;
            this.rightBound = var1;
        }
    }

    public boolean isOverLapping(Interval interval) {
        return  isBetween(this.leftBound, interval.leftBound, interval.rightBound) ||
                isBetween(this.rightBound, interval.leftBound, interval.rightBound) ||
                isBetween(interval.leftBound, this.leftBound, this.rightBound);
    }

    public Interval merge(Interval obj) {
        if (isOverLapping(obj)) {
            int leftBound = min(this.leftBound, obj.leftBound);
            int rightBound = max(this.rightBound, obj.rightBound);
            return new Interval(leftBound, rightBound);
        } else {
            throw new IllegalStateException("Intervals aren't overlapping!!");
        }
    }

    public int clamp(int i){
        if (i <= leftBound)
            return leftBound;
        if (i >= rightBound)
            return rightBound;
        return i;
    }

    private boolean isBetween(int i, int var1, int var2) {
        return (var1 <= i && i <= var2) || (var2 <= i && i <= var1);
    }

    @Override
    public String toString() {
        return "[" + this.leftBound + "," + this.rightBound + "]";
    }

    @Override
    public int compareTo(Object o) {
        if (o != null) {
            Interval interval = (Interval) o;

            if (equals(interval))
                return 0;

            if (this.leftBound < interval.leftBound)
                return -1 * abs(new Random().nextInt() + 1);
            else
                return abs(new Random().nextInt() + 1);

        } else
            throw new NullPointerException();
    }

    @Override
    public boolean equals(Object o) {
        Interval interval = (Interval) o;
        return (this.leftBound == interval.leftBound) && (this.rightBound == interval.rightBound);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Interval(this.leftBound,this.rightBound);
    }
}
