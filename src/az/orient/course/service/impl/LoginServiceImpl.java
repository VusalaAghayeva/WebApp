package az.orient.course.service.impl;

import az.orient.course.dao.LoginDao;
import az.orient.course.model.Login;
import az.orient.course.service.LoginService;

public class LoginServiceImpl implements LoginService {
    private LoginDao loginDao;

    public LoginServiceImpl(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    @Override
    public Login login(String username, String password) throws Exception {
        return loginDao.login(username,password);
    }

    @Override
    public boolean updateTokenForEmail(String token, String email) throws Exception {
        return loginDao.updateTokenForEmail(token,email);
    }

    @Override
    public boolean checkMail(String username) throws Exception {
        return loginDao.checkMail(username);
    }

    @Override
    public boolean changePasswordWithToken(String password, String token) throws Exception {
        return loginDao.changePasswordWithToken(password,token);
    }
}
