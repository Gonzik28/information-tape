package com.gonzik28.information_tape;

import com.gonzik28.information_tape.config.Right;
import com.gonzik28.information_tape.dto.RequestRightDto;
import com.gonzik28.information_tape.dto.ResponseRightDto;
import com.gonzik28.information_tape.dto.utils.RightUtils;
import com.gonzik28.information_tape.entity.RightEntity;
import com.gonzik28.information_tape.entity.UserEntity;
import com.gonzik28.information_tape.repository.RightRepository;
import com.gonzik28.information_tape.repository.UserRepository;
import com.gonzik28.information_tape.service.RightService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class RightServiceTest {
    @InjectMocks
    private RightService rightService;

    @Mock
    private RightRepository rightRepository;

    @Mock
    private UserRepository userRepository;


    @Test
    public void findByIdTest() {
        UserEntity userEntity = new UserEntity();

        RightEntity rightEntity = new RightEntity();
        rightEntity.setId("005");
        rightEntity.setUser(userEntity);
        rightEntity.setUserRight(Right.admin);

        when(rightRepository.findById(any(String.class))).thenReturn(Optional.of(rightEntity));

        ResponseRightDto rightTest = RightUtils.rightEntityToDto(rightEntity);
        ResponseRightDto right = rightService.findById(rightTest.getId());

        assertNotNull(right);
        assertSame("005", rightService.findById(right.getId()).getId());
        assertSame(Right.admin, rightService.findById(right.getId()).getUserRight());
    }

    @Test
    public void createTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("01");
        userEntity.setEnabled(true);
        when(userRepository.findById(any(String.class))).thenReturn(Optional.of(userEntity));

        RightEntity rightEntity = new RightEntity();
        rightEntity.setId("005");
        rightEntity.setUser(userEntity);
        rightEntity.setUserRight(Right.admin);

        when(rightRepository.save(any(RightEntity.class))).thenReturn(rightEntity);

        RequestRightDto requestRightDto = new RequestRightDto();
        requestRightDto.setUserId(rightEntity.getUser().getId());
        ResponseRightDto right = rightService.create(requestRightDto);

        assertNotNull(right);
        assertSame("005", right.getId());
        assertSame(Right.admin, right.getUserRight());
    }

    @Test
    public void updateTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("01");
        userEntity.setEnabled(true);
        when(userRepository.findById(any(String.class))).thenReturn(Optional.of(userEntity));

        RightEntity rightEntity = new RightEntity();
        rightEntity.setId("005");
        rightEntity.setUser(userEntity);
        rightEntity.setUserRight(Right.admin);

        when(rightRepository.findById(any(String.class))).thenReturn(Optional.of(rightEntity));

        when(rightRepository.save(any(RightEntity.class))).thenReturn(rightEntity);

        RequestRightDto requestRightDto = new RequestRightDto();
        requestRightDto.setUserId(rightEntity.getUser().getId());
        requestRightDto.setUserRight(rightEntity.getUserRight());

        ResponseRightDto rightTest = rightService.update(requestRightDto);

        assertNotNull(rightTest);
        assertSame(Right.admin, rightTest.getUserRight());
    }

}
