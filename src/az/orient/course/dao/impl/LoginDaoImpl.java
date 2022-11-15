package az.orient.course.dao.impl;

import az.orient.course.dao.DBHelper;
import az.orient.course.dao.LoginDao;
import az.orient.course.model.Login;
import az.orient.course.model.Role;
import az.orient.course.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDaoImpl implements LoginDao {
    @Override
    public Login login(String username, String password) throws Exception {
        Login login = new Login();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select l.id,username,name,surname,Role_id,Role_name from login l "
                + "inner join role r on l.Role_id=r.id "
                + "where l.active=1 and r.active=1 and "
                + "l.username=? and l.password=?";
        try {
            c = JdbcUtility.getMyConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();

                if (rs.next()) {
                    login.setId(rs.getLong("id"));
                    login.setUsername(rs.getString("username"));
                    login.setName(rs.getString("name"));
                    login.setSurname(rs.getString("surname"));
                    Role role = new Role();
                    role.setId(rs.getLong("Role_id"));
                    role.setRoleName(rs.getString("Role_name"));
                    login.setRole(role);
                } else {
                    login = null;
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return login;
    }

    @Override
    public boolean updateTokenForEmail(String token, String email) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update login set token=?" + " where username=?";
        try {
            c = JdbcUtility.getMyConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, token);
                ps.setString(2, email);
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, null);
        }

        return result;
    }

    @Override
    public boolean changePasswordWithToken(String password, String token) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update login set password=?,TOKEN=null " + " where token=?";
        try {
            c = JdbcUtility.getMyConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, password);
                ps.setString(2, token);
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, null);
        }

        return result;
    }

    @Override
    public boolean checkMail(String username) throws Exception {
        boolean result = false;
        Login login = new Login();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from login l "
                + " where l.active=1 and l.username=? ";
        try {
            c = JdbcUtility.getMyConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, username);
                rs = ps.executeQuery();

                if (rs.next()) {
                    result = true;
                } else {
                    result=false;
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
