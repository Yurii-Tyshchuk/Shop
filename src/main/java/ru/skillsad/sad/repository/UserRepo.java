package ru.skillsad.sad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillsad.sad.domain.user.User;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByName(String s);
}
