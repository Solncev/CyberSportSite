package ru.kpfu.itis.csport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.csport.model.Team;

/**
 * @author krylov
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
}
