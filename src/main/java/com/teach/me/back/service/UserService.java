package com.teach.me.back.service;


import com.teach.me.back.data.dto.user.request.DeleteAccountRequest;
import com.teach.me.back.data.dto.user.request.LoginOrRegisterRequest;
import com.teach.me.back.data.dto.user.request.ModifyNameRequest;
import com.teach.me.back.data.dto.user.response.FindUserResponse;
import com.teach.me.back.data.entity.User;
import com.teach.me.back.data.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User loginOrRegister(LoginOrRegisterRequest request) {

        if (request.password().equals("testuser")) {
            return userRepository.queryById(1L);
        }

        return userRepository.queryById(2L);
    }

    public void deleteUser(DeleteAccountRequest request) {
        User user = userRepository.queryById(request.id());
    }

    public void modifyName(Long token,ModifyNameRequest request) {
        Optional<User> optionalUser = userRepository.findById(token);
        User user = optionalUser.orElseThrow(RuntimeException::new);

        user.modifyName(request);
    }

    public FindUserResponse findUser(Long token) {
        User user = userRepository.queryById(token);
        return user.convertToFindUserResponse();
    }


}
