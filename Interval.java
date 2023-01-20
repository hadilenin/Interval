import java.util.Random;

import static java.lang.Math.*;

public class Interval<T extends Number> implements Comparable,Cloneable {
    private T startBound;
    private T rightBound;

    public Interval(T var1, T var2) {
        if (getValue(var1) <= getValue(var2)) {
            this.startBound = var1;
            this.rightBound = var2;
        } else {
            this.startBound = var2;
            this.rightBound = var1;
        }
    }

    public boolean isOverLapping(Interval interval) {
        return  isBetween(this.startBound, (T) interval.startBound, (T) interval.rightBound) ||
                isBetween(this.rightBound, (T) interval.startBound, (T) interval.rightBound) ||
                isBetween((T) interval.startBound, this.startBound, this.rightBound);
    }

    public Interval merge(Interval obj) {
        if (isOverLapping(obj)) {
            Double leftBound = min(getValue(this.startBound), getValue(obj.startBound));
            Double rightBound = max(getValue(this.rightBound), getValue(obj.rightBound));
            return new Interval(leftBound, rightBound);
        } else {
            throw new IllegalStateException("Intervals aren't overlapping!!");
        }
    }

    public T clamp(T i){
        if (getValue(i) <= getValue(startBound))
            return startBound;
        if (getValue(i) >= getValue(rightBound))
            return rightBound;
        return i;
    }

    private static Double getValue(Number var){
        return var.doubleValue();
    }

    private boolean isBetween(T i, T var1, T var2) {
        return (getValue(var1) <= getValue(i) && getValue(i) <= getValue(var2)) ||
                (getValue(var2) <= getValue(i) && getValue(i) <= getValue(var1));
    }

    @Override
    public String toString() {
        return "[" + getValue(startBound) + "," + getValue(rightBound) + "]";
    }

    @Override
    public int compareTo(Object o) {
        if (o != null) {
            Interval interval = (Interval) o;

            if (equals(interval))
                return 0;

            if (getValue(this.startBound) < getValue(interval.startBound))
                return -1;
            else
                return 1;

        } else
            throw new IllegalArgumentException("Null is not comparable!");
    }

    @Override
    public boolean equals(Object o) {
        Interval interval = (Interval) o;
        return (this.startBound == interval.startBound) && (this.rightBound == interval.rightBound);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Interval(this.startBound,this.rightBound);
    }

    public T getStartBound() {
        return startBound;
    }

    public void setStartBound(T startBound) {
        this.startBound = startBound;
    }

    public T getRightBound() {
        return rightBound;
    }

    public void setRightBound(T rightBound) {
        this.rightBound = rightBound;
    }
}
