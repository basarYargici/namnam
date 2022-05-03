/*
 * Copyright (C) 2022 İ. BAŞAR YARGICI, Fatih Salınmaz and Zeynep Çelik 
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import model.Visitor;

/**
 *
 * @author İ. BAŞAR YARGICI
 */
@ManagedBean(name = "visitor")
@SessionScoped
public class VisitorDomain {

    Result<DataSource> dataSourceResult;
    Result<Connection> connectionResult;
    CachedRowSet rowSet = null;
    Result s;
    Success success;
    Error error;
    ArrayList<Visitor> visitorList;
    String query;

    public VisitorDomain() {
        this.visitorList = new ArrayList();
        DataSourceUtil.getInstance();
        dataSourceResult = getDataSource();
        connectionResult = getConnection();
    }

    /**
     * Check isSuccess in UI. if it is false, pop up the message. You can get
     * data in HTML with visitor.all.data
     *
     * @return Success if no error occurs, with data of List of Visitors. Error
     * if any error occurs, with error message.
     *
     */
    public Result getAll() {
        if (dataSourceResult.isSuccess == false) {
            return dataSourceResult;
        }
        if (connectionResult.isSuccess == false) {
            return connectionResult;
        }

        query = "SELECT * FROM APP.VISITOR";

        try (Statement statement = connectionResult.data.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            Visitor temp;

            while (rs.next()) {
                temp = new Visitor();
                toVisitor(temp, rs);
                visitorList.add(temp);
            }
            return new Success(visitorList);
        } catch (SQLException e) {
            return new Error(e.getMessage());
        }

    }

    private Result getDataSource() {
        try {
            return new Success(DataSourceUtil.getDataSource());
        } catch (SQLException e) {
            return new Error(e.getLocalizedMessage());
        }
    }

    private Result<Connection> getConnection() {
        try {
            return new Success(DataSourceUtil.getConnection());
        } catch (SQLException e) {
            return new Error(e.getLocalizedMessage());
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
