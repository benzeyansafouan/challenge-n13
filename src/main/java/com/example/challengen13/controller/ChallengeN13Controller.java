package com.example.challengen13.controller;

import com.example.challengen13.model.UserInfo;
import com.example.challengen13.model.UserInfoDto;
import com.example.challengen13.service.ChallengeN13ServiceImpl;
import com.example.challengen13.utils.PdfGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:4200")
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
    try{
            var users = challengeN13Service.getAllUsers();
            return ResponseEntity.ok(users);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @PostMapping("/save")
    ResponseEntity<UserInfoDto> save(@RequestParam String userInfo,
                              @RequestParam(required = false) MultipartFile userImage,HttpServletResponse response) {
        try {
            var newUserInfo =new UserInfo();
            var userObject = objectMapper.readValue(userInfo, UserInfoDto.class);
            modelMapper.map(userObject, newUserInfo);
            retrieveImage(userObject,userImage);
            generatePdfFile(response,userObject);
            return ResponseEntity.ok(userObject);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    public void generatePdfFile(HttpServletResponse response,UserInfoDto userInfoDto)
        throws Exception {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);
        PdfGenerator generator = new PdfGenerator();
        generator.generate(userInfoDto, response);
    }


    private void retrieveImage(UserInfoDto userInfoDto,MultipartFile userImage) {
            var resource = userImage.getResource();
            try {
                userInfoDto.setUserImage(resource.getInputStream().readAllBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
