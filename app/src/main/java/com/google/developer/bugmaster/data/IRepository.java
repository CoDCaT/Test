package com.google.developer.bugmaster.data;


import java.util.List;

public interface IRepository<T> {

    void add(T item);
    void remove(T item);
    void update(T item);

    List<T> query();


}
