package com.gonzik28.information_tape.service;

import com.gonzik28.information_tape.entity.AuthorizationEntity;
import com.gonzik28.information_tape.entity.RightEntity;
import com.gonzik28.information_tape.entity.UserEntity;
import com.gonzik28.information_tape.repository.AuthorizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostgresUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthorizationRepository authorizationRepository;

    @Override
    public User loadUserByUsername(String login) throws UsernameNotFoundException {
        AuthorizationEntity authorizationEntity = authorizationRepository.findByLogin(login);
        UserEntity user = authorizationEntity.getUser();
        if (user == null) {
            throw new UsernameNotFoundException("userName " + login + " Not found in the database");
        }
        String passwordHash = authorizationEntity.getPasswordHash();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(passwordHash);

        String userLastName = authorizationEntity.getUser().getLastName();


        boolean enabled = authorizationEntity.getUser().getEnabled();
        boolean notExpiredAccount = true;//аккаунт не истек
        boolean notExpiredCredentials = true;//учетные данные не просрочены
        boolean notLockedAccount = true;//аккаунт не заблокирован


        List<RightEntity> t = new ArrayList<>();
        String y = t.stream().map(RightEntity :: getUserRight).map(Enum :: name).collect(Collectors.joining(","));

        return new User(
                userLastName,
                encodedPassword,
                enabled,
                notExpiredAccount,
                notExpiredCredentials,
                notLockedAccount,
                AuthorityUtils.commaSeparatedStringToAuthorityList(y)
        );
    }

}
