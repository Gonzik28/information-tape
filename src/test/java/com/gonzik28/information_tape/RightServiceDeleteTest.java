package com.gonzik28.information_tape;

import com.gonzik28.information_tape.config.Right;
import com.gonzik28.information_tape.dto.RequestRightDto;
import com.gonzik28.information_tape.dto.RequestUserDto;
import com.gonzik28.information_tape.dto.ResponseRightDto;
import com.gonzik28.information_tape.dto.ResponseUserDto;
import com.gonzik28.information_tape.repository.RightRepository;
import com.gonzik28.information_tape.service.RightService;
import com.gonzik28.information_tape.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@Transactional
public class RightServiceDeleteTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RightService rightService;

    @SpyBean
    private RightRepository rightRepository;

    @Test
    public void deleteTest() {
        LocalDate birthDate = LocalDate.of(1995, 07, 17);

        RequestUserDto userDto = new RequestUserDto();
        userDto.setId(UUID.randomUUID().toString());
        userDto.setFirstName("Никита");
        userDto.setLastName("Баринов");
        userDto.setBirthDate(birthDate);
        ResponseUserDto responseUserDto = userService.create(userDto);

        RequestRightDto requestRightDto = new RequestRightDto();
        requestRightDto.setUserId(responseUserDto.getId());
        requestRightDto.setUserRight(Right.admin);

        ResponseRightDto right = rightService.create(requestRightDto);

        boolean isExistBeforeDelete = rightRepository.findById(right.getId()).isPresent();

        rightService.delete(right.getId());

        boolean isExistAfterDelete = rightRepository.findById(right.getId()).isPresent();

        assertTrue(isExistBeforeDelete);
        assertFalse(isExistAfterDelete);
    }
}
