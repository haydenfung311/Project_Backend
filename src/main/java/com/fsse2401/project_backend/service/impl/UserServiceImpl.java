package com.fsse2401.project_backend.service.impl;

import com.fsse2401.project_backend.data.user.domainObject.FirebaseUserData;
import com.fsse2401.project_backend.data.user.entity.UserEntity;
import com.fsse2401.project_backend.repository.UserRepository;
import com.fsse2401.project_backend.service.UserService;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getEntityByFirebaseUserData(FirebaseUserData firebaseUserData){
//        Optional <UserEntity>userEntityOptional = userRepository.findByFirebaseUid(firebaseUserData.getFirebaseUid())
//         if (userEntityOptional.isEmpty()) {
//             UserEntity userEntity = new UserEntity(firebaseUserData);
//             return userRepository.save(userEntity);
//         } else{
//             return userEntityOptional.get();
//         }

        return userRepository.findByFirebaseUid(firebaseUserData.getFirebaseUid()).orElseGet(
                () -> userRepository.save(new UserEntity(firebaseUserData))

//        Optional<UserEntity> userEntityOptional = userRepository.findByFirebaseUid(firebaseUserData.getFirebaseUid());
//        return userEntityOptional.orElseGet (() -> userRepository.save(new UserEntity(firebaseUserData))
        );
    }
}
