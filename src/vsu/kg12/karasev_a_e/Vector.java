package vsu.kg12.karasev_a_e;

public interface Vector<T extends Vector<T>> {

    void add(T v);
    void sub(T v);

    T multiply(float c);
    void mult(float c);

    T divide(float c);

    void div(float c);

    float length();

    T normal();

    boolean equals(T other);
}