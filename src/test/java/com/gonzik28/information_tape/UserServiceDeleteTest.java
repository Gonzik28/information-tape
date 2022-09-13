package com.gonzik28.information_tape;

import com.gonzik28.information_tape.dto.RequestUserDto;
import com.gonzik28.information_tape.dto.ResponseUserDto;
import com.gonzik28.information_tape.repository.UserRepository;
import com.gonzik28.information_tape.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@Transactional
public class UserServiceDeleteTest {

    @SpyBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void deleteTest() {
        LocalDate date = LocalDate.of(2020, 02, 12);

        RequestUserDto requestUserDto = new RequestUserDto();
        requestUserDto.setId("101");
        requestUserDto.setBirthDate(date);
        requestUserDto.setFirstName("Margo");
        requestUserDto.setLastName("Landik");
        ResponseUserDto user = userService.create(requestUserDto);

        boolean isExistBeforeDelete = userRepository.findById(user.getId()).isPresent();

        userService.delete(user.getId());

        boolean isExistAfterDelete = userRepository.findById(user.getId()).isPresent();

        assertTrue(isExistBeforeDelete);
        assertFalse(isExistAfterDelete);
    }
}
