package com.gonzik28.information_tape.service;

import com.gonzik28.information_tape.dto.RequestUserDto;
import com.gonzik28.information_tape.dto.ResponseUserDto;
import com.gonzik28.information_tape.dto.utils.UserUtils;
import com.gonzik28.information_tape.entity.AuthorizationEntity;
import com.gonzik28.information_tape.entity.UserEntity;
import com.gonzik28.information_tape.repository.AuthorizationRepository;
import com.gonzik28.information_tape.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;


@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorizationRepository authorizationRepository;

    public ResponseUserDto findById(String id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new NoSuchElementException("Пользователя с указанным id = " + id + " не существует");
        } else {
            UserEntity userEntity = userRepository.findById(id).get();
            return UserUtils.userEntityToDto(userEntity);
        }
    }

    public ResponseUserDto create(RequestUserDto requestUserDto) {
        UserEntity userEntity = UserUtils.userDtoRequestToEntity(requestUserDto);
        userEntity.setId(UUID.randomUUID().toString());
        userEntity = userRepository.save(userEntity);
        return UserUtils.userEntityToDto(userEntity);
    }

    public ResponseUserDto update(RequestUserDto requestUserDto) {
        if (!userRepository.findById(requestUserDto.getId()).isPresent()) {
            throw new NoSuchElementException("Пользователя с указанным id = "
                    + requestUserDto.getId() + " не существует");
        } else {
            UserEntity userEntity = userRepository.findById(requestUserDto.getId()).get();
            userEntity.setFirstName(requestUserDto.getFirstName());
            userEntity.setLastName(requestUserDto.getLastName());
            userEntity.setBirthDate(requestUserDto.getBirthDate());
            userEntity = userRepository.save(userEntity);
            return UserUtils.userEntityToDto(userEntity);
        }
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User loadUserByUsername(String login) throws UsernameNotFoundException {
        AuthorizationEntity authorizationEntity = authorizationRepository.findByLogin(login);
        UserEntity user = authorizationEntity.getUser();
        String password = authorizationEntity.getPasswordHash();
        boolean enabled = user.getEnabled();
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new User(user.getFirstName(),
                password,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                AuthorityUtils.commaSeparatedStringToAuthorityList("user"));
    }
}
