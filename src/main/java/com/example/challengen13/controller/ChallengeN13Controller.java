package com.example.challengen13.controller;

import com.example.challengen13.model.UserInfo;
import com.example.challengen13.model.UserInfoDto;
import com.example.challengen13.service.ChallengeN13ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("user")
public class ChallengeN13Controller {


    private final ChallengeN13ServiceImpl challengeN13Service;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    public ChallengeN13Controller(ChallengeN13ServiceImpl challengeN13Service,
                                  ModelMapper modelMapper, ObjectMapper objectMapper) {
        this.challengeN13Service = challengeN13Service;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }
    @GetMapping("/get-users")
    ResponseEntity<List<UserInfoDto>> getUsers() {
        try {
            var users = challengeN13Service.getAllUsers();
            return ResponseEntity.ok(users);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @PostMapping("/save")
    ResponseEntity<UserInfoDto> save(@RequestParam String userInfo,
                                     @RequestParam(required = false) MultipartFile userImage) {
        try {
            var newUserInfo =new UserInfo();
            var userObject = objectMapper.readValue(userInfo, UserInfo.class);
            modelMapper.map(userObject, newUserInfo);
            UserInfo savedUser = challengeN13Service.saveInfos(newUserInfo, userImage);
            var userInfoDto = modelMapper.map(savedUser,UserInfoDto.class);
            retrieveImage(savedUser, userInfoDto);
            return ResponseEntity.ok(userInfoDto);
        } catch (IOException exception) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


    private void retrieveImage(UserInfo userInfo, UserInfoDto userInfoDto) {
        if (userInfo.getImageFileId() != null) {
            var resource = challengeN13Service.getUserImageById(userInfo.getImageFileId());
            try {
                userInfoDto.setUserImage(resource.getInputStream().readAllBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
