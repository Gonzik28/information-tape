package com.gonzik28.information_tape.service;

import com.gonzik28.information_tape.dto.RequestAuthorizationDto;
import com.gonzik28.information_tape.dto.ResponseAuthorizationDto;
import com.gonzik28.information_tape.dto.utils.AuthorizationUtils;
import com.gonzik28.information_tape.entity.AuthorizationEntity;
import com.gonzik28.information_tape.entity.UserEntity;
import com.gonzik28.information_tape.repository.AuthorizationRepository;
import com.gonzik28.information_tape.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class AuthorizationService {
    @Autowired
    private AuthorizationRepository authorizationRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseAuthorizationDto findById(String id) {
        AuthorizationEntity authorizationEntity = authorizationRepository.findById(id).get();
        return AuthorizationUtils.authorizationEntityToDto(authorizationEntity);
    }

    public ResponseAuthorizationDto findByLoginPassword(String login, String password) {
        String passwordHash = DigestUtils.md5Hex(password);
        AuthorizationEntity authorizationEntity =
                authorizationRepository.findByLoginAndPasswordHash(login, passwordHash);
        return AuthorizationUtils.authorizationEntityToDto(authorizationEntity);
    }

    public ResponseAuthorizationDto create(RequestAuthorizationDto requestAuthorizationDto) {
        String userId = requestAuthorizationDto.getUserId();
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId); //TODO равен null
        UserEntity userEntity = userEntityOptional.get();
        requestAuthorizationDto.setPassword(requestAuthorizationDto.getPassword());
        AuthorizationEntity authorizationEntity = AuthorizationUtils.authorizationRequestDtoToEntity(
                requestAuthorizationDto, userEntity);
        authorizationEntity.setId(UUID.randomUUID().toString());
        authorizationEntity = authorizationRepository.save(authorizationEntity);
        return AuthorizationUtils.authorizationEntityToDto(authorizationEntity);
    }

    public void delete(String id) {
        authorizationRepository.deleteById(id);
    }
}
