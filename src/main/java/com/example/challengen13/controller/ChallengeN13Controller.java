package com.example.challengen13.controller;

import com.example.challengen13.model.UserInfo;
import com.example.challengen13.model.UserInfoDto;
import com.example.challengen13.service.ChallengeN13ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.modelmapper.AbstractCondition;
import org.modelmapper.Condition;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@EnableAutoConfiguration
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
        if (userInfo.getImageFileObjectId() != null) {
            var resource = challengeN13Service.getUserImageById(userInfo.getImageFileObjectId());
            try {
                userInfoDto.setUserImage(resource.getInputStream().readAllBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
