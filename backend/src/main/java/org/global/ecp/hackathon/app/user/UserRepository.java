package org.global.ecp.hackathon.app.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getUserByUsername(final String username);

    Boolean existsUserByEmail(final String email);

    Boolean existsUserByUsername(final String username);

}
