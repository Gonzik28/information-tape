package com.gonzik28.information_tape;

import com.gonzik28.information_tape.dto.RequestAuthorizationDto;
import com.gonzik28.information_tape.dto.ResponseAuthorizationDto;
import com.gonzik28.information_tape.dto.utils.AuthorizationUtils;
import com.gonzik28.information_tape.entity.AuthorizationEntity;
import com.gonzik28.information_tape.entity.UserEntity;
import com.gonzik28.information_tape.repository.AuthorizationRepository;
import com.gonzik28.information_tape.repository.UserRepository;
import com.gonzik28.information_tape.service.AuthorizationService;
import com.gonzik28.information_tape.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class AuthorizationServiceTest {

    @InjectMocks
    private AuthorizationService authorizationService;

    @Mock
    private AuthorizationRepository authorizationRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    public void findByIdTest() {
        UserEntity userEntity = new UserEntity();

        AuthorizationEntity authorizationEntity = new AuthorizationEntity();
        authorizationEntity.setId("001");
        authorizationEntity.setUser(userEntity);
        authorizationEntity.setLogin("@Ladoga");
        authorizationEntity.setPasswordHash("12345!");

        when(authorizationRepository.findById(any(String.class))).thenReturn(Optional.of(authorizationEntity));

        ResponseAuthorizationDto authorizationTest = AuthorizationUtils.authorizationEntityToDto(authorizationEntity);
        ResponseAuthorizationDto authorization = authorizationService.findById(authorizationTest.getId());

        assertNotNull(authorization);
        assertSame("001", authorization.getId());
        assertSame("@Ladoga", authorization.getLogin());
    }

    @Test
    public void findByLoginPasswordTest() {
        UserEntity userEntity = new UserEntity();

        AuthorizationEntity authorizationEntity = new AuthorizationEntity();
        authorizationEntity.setId("001");
        authorizationEntity.setUser(userEntity);
        authorizationEntity.setLogin("@Ladoga");
        authorizationEntity.setPasswordHash("12345!");

        when(authorizationRepository.findByLoginAndPasswordHash(any(String.class), any(String.class)))
                .thenReturn(authorizationEntity);

        ResponseAuthorizationDto authorizationTest = AuthorizationUtils.authorizationEntityToDto(authorizationEntity);
        ResponseAuthorizationDto authorization =
                authorizationService.findByLoginPassword(authorizationTest.getLogin(), "12345!");

        assertNotNull(authorization);
        assertSame("001", authorization.getId());
        assertSame("@Ladoga", authorization.getLogin());
    }

    @Test
    public void createTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("111");
        userEntity.setEnabled(true);
        when(userRepository.findById(any(String.class))).thenReturn(Optional.of(userEntity));

        AuthorizationEntity authorizationEntity = new AuthorizationEntity();
        authorizationEntity.setId("001");
        authorizationEntity.setUser(userEntity);
        authorizationEntity.setLogin("@Ladoga");
        authorizationEntity.setPasswordHash("12345!");

        when(authorizationRepository.save(any(AuthorizationEntity.class))).thenReturn(authorizationEntity);

        RequestAuthorizationDto requestAuthorizationDto = new RequestAuthorizationDto();
        requestAuthorizationDto.setUserId(authorizationEntity.getUser().getId());
        ResponseAuthorizationDto authorization = authorizationService.create(requestAuthorizationDto);

        assertNotNull(authorization);
        assertSame("001", authorization.getId());
        assertSame("@Ladoga", authorization.getLogin());
    }

}