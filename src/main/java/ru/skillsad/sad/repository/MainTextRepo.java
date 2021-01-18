package ru.skillsad.sad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillsad.sad.domain.general.MainText;
@Repository
public interface MainTextRepo extends JpaRepository<MainText, Long> {
    MainText findByText(String s);
    MainText getById(Long l);
}
