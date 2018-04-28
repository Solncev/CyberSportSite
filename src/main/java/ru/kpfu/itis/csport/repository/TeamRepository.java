package ru.kpfu.itis.csport.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.csport.model.Team;

public interface TeamRepository extends CrudRepository<Team, Integer> {
}
