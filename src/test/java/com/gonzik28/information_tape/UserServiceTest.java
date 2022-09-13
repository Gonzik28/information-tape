package com.gonzik28.information_tape;

import com.gonzik28.information_tape.dto.RequestUserDto;
import com.gonzik28.information_tape.dto.ResponseUserDto;
import com.gonzik28.information_tape.dto.utils.UserUtils;
import com.gonzik28.information_tape.entity.UserEntity;
import com.gonzik28.information_tape.repository.UserRepository;
import com.gonzik28.information_tape.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;


    @Test
    public void findByIdTest() {
        LocalDate date = LocalDate.of(2020, 02, 12);
        UserEntity userEntity = new UserEntity();
        userEntity.setId("01");
        userEntity.setFirstName("Margo");
        userEntity.setLastName("Landik");
        userEntity.setBirthDate(date);

        when(userRepository.findById(any(String.class))).thenReturn(Optional.of(userEntity));

        ResponseUserDto userTest = UserUtils.userEntityToDto(userEntity);
        ResponseUserDto user = userService.findById(userTest.getId());

        assertNotNull(user);
        assertSame("01", userService.findById(user.getId()).getId());
        assertSame("Margo", userService.findById(user.getId()).getFirstName());
        assertSame("Landik", userService.findById(user.getId()).getLastName());
        assertSame(date, userService.findById(user.getId()).getBirthDate());
    }

    @Test
    public void createTest() {
        LocalDate date = LocalDate.of(2020, 02, 12);
        UserEntity userEntity = new UserEntity();
        userEntity.setId("01");
        userEntity.setFirstName("Margo");
        userEntity.setLastName("Landik");
        userEntity.setBirthDate(date);

        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        ResponseUserDto user = userService.create(new RequestUserDto());

        assertNotNull(user);
        assertSame("01", user.getId());
        assertSame("Margo", user.getFirstName());
        assertSame("Landik", user.getLastName());
        assertSame(date, user.getBirthDate());
    }

    @Test
    public void updateTest() {
        LocalDate date = LocalDate.of(2020, 02, 12);
        UserEntity userEntity = new UserEntity();
        userEntity.setId("01");
        userEntity.setFirstName("Margo");
        userEntity.setLastName("Landik");
        userEntity.setBirthDate(date);
        UserUtils.userEntityToDto(userEntity);

        when(userRepository.findById(any(String.class))).thenReturn(Optional.of(userEntity));

        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        RequestUserDto requestUserDto = new RequestUserDto();
        requestUserDto.setId(userEntity.getId());
        requestUserDto.setFirstName(userEntity.getFirstName());
        requestUserDto.setLastName("Gonina");
        requestUserDto.setBirthDate(userEntity.getBirthDate());

        ResponseUserDto userTest = userService.update(requestUserDto);

        assertNotNull(userTest);
        assertSame("01", userTest.getId());
        assertSame("Margo", userTest.getFirstName());
        assertSame("Gonina", userTest.getLastName());
        assertSame(date, userTest.getBirthDate());
    }

}
