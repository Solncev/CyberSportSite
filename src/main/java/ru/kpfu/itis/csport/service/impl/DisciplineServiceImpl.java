package ru.kpfu.itis.csport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.csport.model.Discipline;
import ru.kpfu.itis.csport.repository.DisciplineRepository;
import ru.kpfu.itis.csport.service.DisciplineService;

import java.util.List;

@Service
public class DisciplineServiceImpl implements DisciplineService {
    private final DisciplineRepository disciplineRepository;

    @Autowired
    public DisciplineServiceImpl(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    @Override
    public void add(Discipline discipline) throws Exception {
        try {
            disciplineRepository.save(discipline);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @Override
    public void delete(Discipline discipline) {
        disciplineRepository.delete(discipline);
    }

    @Modifying
    @Override
    public void update(Discipline discipline) {
        disciplineRepository.save(discipline);
    }

    @Override
    public Discipline find(int id) {
        return disciplineRepository.findOne(id);
    }

    @Override
    public List<Discipline> findAll() {
        return disciplineRepository.findAll();
    }
}
