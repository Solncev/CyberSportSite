package ru.kpfu.itis.csport.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.csport.model.User;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/12/18 10:11 PM
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}
