import static java.lang.Math.*;

public class Interval<T extends Number> implements Comparable,Cloneable {
    private T startBound;
    private T endBound;

    public Interval(T var1, T var2) {
        if (getValue(var1) <= getValue(var2)) {
            this.startBound = var1;
            this.endBound = var2;
        } else {
            this.startBound = var2;
            this.endBound = var1;
        }
    }

    public boolean isOverLapping(Interval interval) {
        return  isBetween(this.startBound, (T) interval.startBound, (T) interval.endBound) ||
                isBetween(this.endBound, (T) interval.startBound, (T) interval.endBound) ||
                isBetween((T) interval.startBound, this.startBound, this.endBound);
    }

    public Interval merge(Interval obj) {
        if (isOverLapping(obj)) {
            Double leftBound = min(getValue(this.startBound), getValue(obj.startBound));
            Double rightBound = max(getValue(this.endBound), getValue(obj.endBound));
            return new Interval(leftBound, rightBound);
        } else {
            throw new IllegalStateException("Intervals aren't overlapping!!");
        }
    }

    public T clamp(T i){
        if (getValue(i) <= getValue(startBound))
            return startBound;
        if (getValue(i) >= getValue(endBound))
            return endBound;
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
        return "[" + getValue(startBound) + "," + getValue(endBound) + "]";
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
        return (this.startBound == interval.startBound) && (this.endBound == interval.endBound);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Interval(this.startBound,this.endBound);
    }

    public T getStartBound() {
        return startBound;
    }

    public void setStartBound(T startBound) {
        this.startBound = startBound;
    }

    public T getEndBound() {
        return endBound;
    }

    public void setEndBound(T endBound) {
        this.endBound = endBound;
    }
}
