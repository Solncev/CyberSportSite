package ru.kpfu.itis.csport.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.csport.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}
