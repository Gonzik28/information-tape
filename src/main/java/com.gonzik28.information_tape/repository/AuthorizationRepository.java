package com.gonzik28.information_tape.repository;

import com.gonzik28.information_tape.entity.AuthorizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorizationRepository extends JpaRepository<AuthorizationEntity, String> {
    AuthorizationEntity findByLoginAndPasswordHash(String login, String passwordHash);

    AuthorizationEntity findByLogin(String login);

}
