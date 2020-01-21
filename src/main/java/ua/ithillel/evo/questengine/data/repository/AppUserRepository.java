package ua.ithillel.evo.questengine.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ithillel.evo.questengine.data.entity.AppUser;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findUserByEmailAndPassword(String email, String password);
    Optional<AppUser> findUserByEmail(String email);
    AppUser findByUserName(String userName);
}
