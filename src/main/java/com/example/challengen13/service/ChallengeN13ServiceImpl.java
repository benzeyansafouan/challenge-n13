package com.example.challengen13.service;

import com.example.challengen13.model.UserInfo;
import com.example.challengen13.repository.ChallengeN13Repository;
import java.io.IOException;
import org.bson.types.ObjectId;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ChallengeN13ServiceImpl implements ChallengeN13Service{

    private final ChallengeN13Repository challengeN13Repository;
    private final UserGridFsService userGridFsService;

    public ChallengeN13ServiceImpl(ChallengeN13Repository challengeN13Repository,
                                   UserGridFsService userGridFsService) {
        this.challengeN13Repository = challengeN13Repository;
        this.userGridFsService = userGridFsService;
    }

    @Override
    public UserInfo saveInfos(UserInfo userInfo, MultipartFile multipartFile) throws IOException {
        if (multipartFile != null){
            var imageFileId = userGridFsService.saveFile(multipartFile.getResource(),userInfo.getId(),userInfo.getFirstName());
            userInfo.setImageFileObjectId(imageFileId);
        }
        if (userInfo.getId() == null) {
            challengeN13Repository.save(userInfo);
        }
        if (userInfo.getId().isEmpty()) {
            userInfo.setId(new ObjectId().toString());
            challengeN13Repository.save(userInfo);
        }
        return null;
    }

    public Resource getUserImageById(String imageId) {
        return userGridFsService.getFile(imageId);
    }
    
}
