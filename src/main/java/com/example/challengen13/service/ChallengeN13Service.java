package com.example.challengen13.service;

import com.example.challengen13.model.UserInfo;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface ChallengeN13Service {
    UserInfo saveInfos(UserInfo userInfo, MultipartFile multipartFile) throws IOException;
}
