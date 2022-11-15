package az.orient.course.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Locale;

public class DBHelper {
    public static Connection getConnetcion() throws Exception {
        Locale.setDefault(Locale.ENGLISH);
        Context context = new InitialContext();//context faylinin icini oxuyum deye yaratdim obyektini
        DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/videoders");//meta-inf in icine gire bilim deye bu kodu yaziram.
        Connection c = dataSource.getConnection("root","12345");

     return c;

    }
}
