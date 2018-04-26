package ru.kpfu.itis.csport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.csport.model.Tournament;

import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/26/18 11:37 PM
 */
public interface TournamentRepository extends JpaRepository<Tournament, Integer> {

    //todo
    List<Tournament> getAllByStatus(String status);
}
