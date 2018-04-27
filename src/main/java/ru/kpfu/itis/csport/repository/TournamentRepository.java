package ru.kpfu.itis.csport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.csport.model.Tournament;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/26/18 11:37 PM
 */
public interface TournamentRepository extends JpaRepository<Tournament, Integer> {
    List<Tournament> getAllByStatus(Tournament.Status status);
}
