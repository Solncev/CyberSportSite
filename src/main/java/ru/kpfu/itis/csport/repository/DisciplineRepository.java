package ru.kpfu.itis.csport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.csport.model.Discipline;

public interface DisciplineRepository extends JpaRepository<Discipline, Integer> {
}
