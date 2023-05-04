package com.gachon.frimo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.gachon.frimo.domain.user.User;
import com.gachon.frimo.domain.user.UserRepository;
import com.gachon.frimo.web.dto.UserDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Transactional
    public void UserRegist(UserDto.RegistRequestDto registRequestDto) {
        userRepository.save(registRequestDto.toEntity());
    }

    @Transactional
    public boolean checkUserNNDuplicate(String nickname) {
        return userRepository.existsByUserNN(nickname);
    }

    @Transactional
    public void deleteUser(Long userPk) {
        userRepository.deleteByUserPk(userPk);
    }

    @Transactional
    public UserDto.GetUserOnlyInfoResponseDto getUserInfoResponseDto(Long userPk) {
        User user = userRepository.findByUserPk(userPk);
        return UserDto.GetUserOnlyInfoResponseDto.builder()
                .userId(user.getUserId())
                .userNN(user.getUserNN())
                .userPk(user.getUserPk())
                .build();

    }
    @Transactional
    public Long getUserPk(String userNN) {
        return userRepository.findByUserNN(userNN).getUserPk();
    }

}
