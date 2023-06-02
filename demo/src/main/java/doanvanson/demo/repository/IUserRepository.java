package doanvanson.demo.repository;

import doanvanson.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FORM User u WHERE u.username =?1")
    User findByUsername(String username);

}

