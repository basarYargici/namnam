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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author İ. BAŞAR YARGICI
 */
@ManagedBean(name = "visitor")
@SessionScoped
public class VisitorDomain {

    DataSource dataSource;
    Connection connection;
    Result dataSourceResult;
    Result connectionResult;
    CachedRowSet rowSet = null;
    Result s;

    public VisitorDomain() {
        DataSourceUtil.getInstance();
        dataSourceResult = getDataSource();
        connectionResult = getConnection();
    }

    // check isSuccess in UI. if it is false, pop up the message.
    public Result getAll() {
        if (dataSourceResult.isSuccess == false) {
            return dataSourceResult;
        }
        if (connectionResult.isSuccess == false) {
            return connectionResult;
        }

        try {
            PreparedStatement ps = connection.
                    prepareStatement("SELECT * FROM APP.VISITOR FETCH FIRST 5 ROWS ONLY");
            rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate(ps.executeQuery());
                    
            s = new Success(rowSet);
            return s;
        } catch (SQLException e) {
            s = new Error(e.getMessage());
            return s;
        }
    }

    private Result getDataSource() {
        try {
            dataSource = DataSourceUtil.getDataSource();
            return new Success(dataSource);
        } catch (SQLException e) {
            return new Error(e.getLocalizedMessage());
        }
    }

    private Result<Connection> getConnection() {
        try {
            connection = DataSourceUtil.getConnection();
            return new Success(connection);
        } catch (SQLException e) {
            return new Error(e.getLocalizedMessage());
        }
    }
}
