package ru.kpfu.itis.csport.service;

import ru.kpfu.itis.csport.model.Discipline;

import java.util.List;

public interface DisciplineService {
    void add(Discipline discipline) throws Exception;

    void delete(Discipline discipline);

    void update(Discipline discipline);

    Discipline find(int id);

    List<Discipline> findAll();
}
