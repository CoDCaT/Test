package com.google.developer.bugmaster.data.repository;


import android.content.Context;

import com.google.developer.bugmaster.data.specification.Specification;

import java.util.List;

public interface IRepository<T> {

    void add(T item);
    void remove(T item);
    void update(T item);

    List<T> query(Specification specification);

    String readSharedSetting(Context ctx, String settingName, String defaultValue);

    void saveSharedSetting(Context ctx, String settingName, String settingValue);
}
