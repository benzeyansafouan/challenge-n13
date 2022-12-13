package com.example.challengen13.service;

import com.example.challengen13.model.UserInfo;
import com.example.challengen13.model.UserInfoDto;
import com.example.challengen13.repository.ChallengeN13Repository;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ChallengeN13ServiceImpl implements ChallengeN13Service{

    private final ChallengeN13Repository challengeN13Repository;
    private final UserGridFsService userGridFsService;
    private final ModelMapper modelMapper;

    public ChallengeN13ServiceImpl(ChallengeN13Repository challengeN13Repository,
                                   UserGridFsService userGridFsService,
                                   ModelMapper modelMapper) {
        this.challengeN13Repository = challengeN13Repository;
        this.userGridFsService = userGridFsService;
        this.modelMapper=modelMapper;
    }

    @Override
    public UserInfo saveInfos(UserInfo userInfo, MultipartFile multipartFile) throws IOException {
        if (multipartFile != null){
            var imageFileId = userGridFsService.saveFile(multipartFile.getResource(),userInfo.getId(),userInfo.getFirstName());
            userInfo.setImageFileId(imageFileId);
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

    @Override
    public List<UserInfoDto> getAllUsers() throws Exception {
        var users= challengeN13Repository.findAll();
        var usersDto = users.stream().map(user -> modelMapper.map(user,UserInfoDto.class)).collect(
            Collectors.toList());
        return usersDto;
    }


    public Resource getUserImageById(String imageId) {
        return userGridFsService.getFile(imageId);
    }

}
