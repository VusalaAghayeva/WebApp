package az.orient.course.dao;

import az.orient.course.model.Login;

public interface LoginDao {
    Login login(String username, String password) throws Exception;
    boolean updateTokenForEmail(String token, String email) throws Exception;
    boolean changePasswordWithToken(String password, String token) throws Exception;
    boolean checkMail( String username) throws Exception;

}
