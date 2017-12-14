package com.google.developer.bugmaster.data.repository;

import android.content.Context;

import com.google.developer.bugmaster.data.Insect;
import java.util.List;

public interface IRepository {

    List<Insect> getAllInsect();
    List<Insect> getCurrentInsect();
    String readFromPreference(String key);
    void saveToPreference(String value);
}
