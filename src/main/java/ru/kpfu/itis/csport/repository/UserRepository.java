package ru.kpfu.itis.csport.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.csport.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
    User findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update User u set u.username=:username, u.email=:email where u.id=:id")
    void updateUser(@Param("username") String username, @Param("email") String email,
                    @Param("id") int id);

    @Transactional
    @Modifying
    @Query("update User u set u.password=:password where u.id=:id")
    void changePassword(@Param("password") String password, @Param("id") int id);
}
