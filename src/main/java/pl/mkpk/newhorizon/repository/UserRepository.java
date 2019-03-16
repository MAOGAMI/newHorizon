package pl.mkpk.newhorizon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mkpk.newhorizon.model.User;

@Repository()
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
