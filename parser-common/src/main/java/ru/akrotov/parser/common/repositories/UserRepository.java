package ru.akrotov.parser.common.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.akrotov.parser.common.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT coalesce(max(user.id),0) FROM User user")
    Long getMaxId();
}
