package com.google.developer.bugmaster.data.repository;

import com.google.developer.bugmaster.data.Insect;
import java.util.List;

public interface IRepository {

    List<Insect> getAllInsect(String columnNameFriendlyName);
}
