package com.gonzik28.information_tape.service;

import com.gonzik28.information_tape.dto.RequestRightDto;
import com.gonzik28.information_tape.dto.ResponseRightDto;
import com.gonzik28.information_tape.dto.utils.RightUtils;
import com.gonzik28.information_tape.entity.RightEntity;
import com.gonzik28.information_tape.entity.UserEntity;
import com.gonzik28.information_tape.repository.RightRepository;
import com.gonzik28.information_tape.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class RightService {
    @Autowired
    private RightRepository rightRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseRightDto findById(String id) {
        RightEntity rightEntity = rightRepository.findById(id).get();
        return RightUtils.rightEntityToDto(rightEntity);
    }

    public ResponseRightDto create(RequestRightDto requestRightDto) {
        String userId = requestRightDto.getUserId();
        UserEntity userEntity = userRepository.findById(userId).get();
        RightEntity rightEntity = RightUtils.rightRequestDtoToEntity(requestRightDto, userEntity);
        rightEntity.setId(UUID.randomUUID().toString());
        rightEntity = rightRepository.save(rightEntity);
        return RightUtils.rightEntityToDto(rightEntity);
    }

    public ResponseRightDto update(RequestRightDto requestRightDto) {
        RightEntity rightEntity = rightRepository.findById(requestRightDto.getUserId()).get();
        UserEntity userEntity = userRepository.findById(requestRightDto.getUserId()).get();
        rightEntity.setUserRight(requestRightDto.getUserRight());
        rightEntity.setUser(userEntity);
        rightEntity = rightRepository.save(rightEntity);
        return RightUtils.rightEntityToDto(rightEntity);
    }

    public void delete(String id) {
        rightRepository.deleteById(id);
    }
}
