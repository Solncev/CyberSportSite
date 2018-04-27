package ru.kpfu.itis.csport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.csport.model.ComputerGame;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/26/18 11:22 PM
 */
@Repository
public interface ComputerGameRepository extends JpaRepository<ComputerGame, Integer> {
}
