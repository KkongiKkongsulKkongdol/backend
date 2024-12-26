package com.teach.me.back.controller;

import com.teach.me.back.data.dto.user.request.DeleteAccountRequest;
import com.teach.me.back.data.dto.user.request.LoginOrRegisterRequest;
import com.teach.me.back.data.dto.user.request.ModifyNameRequest;
import com.teach.me.back.data.dto.user.response.FindUserResponse;
import com.teach.me.back.data.dto.user.response.LoginResponse;
import com.teach.me.back.data.entity.User;
import com.teach.me.back.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping
    public ResponseEntity<LoginResponse> loginOrRegister(@RequestBody LoginOrRegisterRequest request) {

        User user = userService.loginOrRegister(request);
        return new ResponseEntity<>(new LoginResponse(user.getUserId()), HttpStatus.OK);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@RequestBody Long token) {
        userService.deleteUser(new DeleteAccountRequest(token));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void modifyName(@RequestParam Long token,@RequestBody ModifyNameRequest request) {
        userService.modifyName(token,request);
    }

    @GetMapping
    public ResponseEntity<FindUserResponse> findUser(@RequestParam Long token) {
        FindUserResponse response = userService.findUser(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}


