package frame.system.vector;

import java.util.Arrays;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Vector {
    private double[] values;

    public Vector(double... values) {
        this.values = values;
    }

    public Vector(int size) {
        values = new double[size];
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    public double[] getValues() {
        return values;
    }

    public double getIndex(int index) {
        return values[index];
    }

    public void setIndex(int index, double value) {
        values[index] = value;
    }

    public int getSize() {
        return values.length;
    }

    public Vector expandFirst(int amount) {
        return new Vector(DoubleStream.concat(Arrays.stream(new double[amount]), Arrays.stream(values)).toArray());
    }

    public Vector expandLast(int amount) {
        return new Vector(DoubleStream.concat(Arrays.stream(values), Arrays.stream(new double[amount])).toArray());
    }

    public double magnitude() {
        return Math.sqrt(Arrays.stream(values).map(d -> d*d).sum());
    }

    public Vector transform(DoubleUnaryOperator op) {
        return new Vector(Arrays.stream(values).map(op).toArray());
    }

    public Vector unitVector() {
        double magnitude = magnitude();

        return transform(d -> d/magnitude);
    }

    public double dot(Vector other) {
        return IntStream.range(0, values.length).mapToDouble(i -> values[i] * other.getIndex(i)).sum();
    }

    public Vector addElements(Vector other) {
        return new Vector(IntStream.range(0, values.length).mapToDouble(i -> values[i] + other.getIndex(i)).toArray());
    }

    @Override
    public String toString() {
        return DoubleStream.of(values).boxed().collect(Collectors.toList()).toString();
    }
}
