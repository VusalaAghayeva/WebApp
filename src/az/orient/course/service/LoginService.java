package az.orient.course.service;

import az.orient.course.model.Login;

public interface LoginService {
    Login login(String username,String password) throws Exception;

    boolean updateTokenForEmail(String token, String email) throws Exception;
    boolean checkMail( String username) throws Exception;
    boolean changePasswordWithToken(String password, String token) throws Exception;
}
