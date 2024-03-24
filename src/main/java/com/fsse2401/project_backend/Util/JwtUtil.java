package com.fsse2401.project_backend.Util;

import com.fsse2401.project_backend.data.user.domainObject.FirebaseUserData;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class JwtUtil {
    public static FirebaseUserData getFirebaseUserData (JwtAuthenticationToken jwtToken){
        return new FirebaseUserData(jwtToken);
    }
}
