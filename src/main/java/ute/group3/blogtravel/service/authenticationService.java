package ute.group3.blogtravel.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ute.group3.blogtravel.dto.AuthenticationResponse;
import ute.group3.blogtravel.security.JwtProvider;

import javax.servlet.http.HttpSession;
@Service
@AllArgsConstructor
public class authenticationService {
    private JwtProvider jwtProvider;
    public boolean requestValid( HttpSession session){
        if(session.getAttribute("authentication")==null){
            return false;
        }
        AuthenticationResponse authentication=(AuthenticationResponse) session.getAttribute("authentication");
        if(jwtProvider.validateToken(authentication.getAuthenticationToken())){
            return true;
        }
        return true;
    }

    public String getUserByCokie(HttpSession session) {
        AuthenticationResponse authentication=(AuthenticationResponse) session.getAttribute("authentication");
       return jwtProvider.getUsernameFromJwt(authentication.getAuthenticationToken());
    }
}
