package com.fsse2401.project_backend.repository;

import com.fsse2401.project_backend.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository <UserEntity, Integer> {
    Optional<UserEntity> findByFirebaseUid (String firebaseUid);
}
