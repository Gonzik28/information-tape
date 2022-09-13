package com.gonzik28.information_tape;

import com.gonzik28.information_tape.dto.RequestAuthorizationDto;
import com.gonzik28.information_tape.dto.RequestUserDto;
import com.gonzik28.information_tape.dto.ResponseAuthorizationDto;
import com.gonzik28.information_tape.dto.ResponseUserDto;
import com.gonzik28.information_tape.repository.AuthorizationRepository;
import com.gonzik28.information_tape.service.AuthorizationService;
import com.gonzik28.information_tape.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.Assert.*;

@SpringBootTest
@Transactional
public class AuthorizationServiceDeleteTest {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private UserService userService;

    @SpyBean
    private AuthorizationRepository authorizationRepository;

    @Test
    public void deleteTest() {
        LocalDate birthDate = LocalDate.of(1995, 07, 17);

        RequestUserDto userDto = new RequestUserDto();
        userDto.setId(UUID.randomUUID().toString());
        userDto.setFirstName("Никита");
        userDto.setLastName("Баринов");
        userDto.setBirthDate(birthDate);
        ResponseUserDto responseUserDto = userService.create(userDto);

        RequestAuthorizationDto requestAuthorizationDto = new RequestAuthorizationDto();
        requestAuthorizationDto.setUserId(responseUserDto.getId());
        requestAuthorizationDto.setLogin("@Nikita_Barinov");
        requestAuthorizationDto.setPassword("пароль");

        ResponseAuthorizationDto authorization = authorizationService.create(requestAuthorizationDto);

        boolean isExistBeforeDelete = authorizationRepository.findById(authorization.getId()).isPresent();

        authorizationService.delete(authorization.getId());

        boolean isExistAfterDelete = authorizationRepository.findById(authorization.getId()).isPresent();

        assertTrue(isExistBeforeDelete);
        assertFalse(isExistAfterDelete);
    }

}