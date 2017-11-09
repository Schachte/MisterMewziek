package LoginApp;

import dbUtil.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    Connection connection;

    public LoginModel() {
        try {
            this.connection = DBConnection.getConnection();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (this.connection == null) {
            System.exit(1);
        }
    }

    /**
     * Only allow login if database connection is successful
     * @return
     */
    public boolean isDatabaseConnected() {
        return this.connection != null;
    }

    /**
     * Checks the authenticity of a user logging into the system
     * @param user
     * @param password
     * @param opt
     * @return
     * @throws Exception
     */
    public boolean isLogin(String user, String password, String opt) throws Exception {

        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql  = "SELECT * FROM login where username = ? and password = ? and division = ?";

        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, user);
            pr.setString(2, password);
            pr.setString(3, opt);

            rs = pr.executeQuery();
            boolean bool1;

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        finally
        {
            pr.close();
            rs.close();
        }
    }
}
