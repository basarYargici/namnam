/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.sql.rowset.CachedRowSet;
import model.Error;
import model.Result;
import model.Success;
import model.Visitor;

/**
 *
 * @author fthsl
 */
@ManagedBean(name = "login")
@SessionScoped
public class LoginDomain extends BaseDomain{
    CachedRowSet rowSet = null;
    Result s;
    Success success;
    model.Error error;
    ArrayList<Visitor> visitorList;
    String query;

    public LoginDomain() {
        this.visitorList = new ArrayList();
    }

    public boolean isLoggedIn(int id) throws SQLException {
        

        query = "SELECT IS_LOGGED_IN FROM APP.VISITOR WHERE ID =" + id;

        try (Statement statement = connectionResult.data.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            Visitor visitor = new Visitor();

            if (!rs.next()) {
                return false;
            }
            while (rs.next()) {
                toVisitor(visitor, rs);
            }
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    private void toVisitor(Visitor temp, ResultSet rs) throws SQLException {
        temp.setId(rs.getInt("ID"));
        temp.setUsername(rs.getString("USERNAME"));
        temp.setPassword(rs.getString("PASSWORD"));
        temp.setName(rs.getString("NAME"));
        temp.setSurname(rs.getString("SURNAME"));
        temp.setMail(rs.getString("MAIL"));
    }
    
    
    
}
