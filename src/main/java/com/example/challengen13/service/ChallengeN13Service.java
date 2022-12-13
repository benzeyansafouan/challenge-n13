package com.example.challengen13.service;

import com.example.challengen13.model.UserInfo;
import com.example.challengen13.model.UserInfoDto;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ChallengeN13Service {
    UserInfo saveInfos(UserInfo userInfo, MultipartFile multipartFile) throws IOException;
    List<UserInfoDto> getAllUsers() throws Exception;
}
