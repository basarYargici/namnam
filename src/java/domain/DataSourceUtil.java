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
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * There should be only one data source in this project and it should not be
 * created more than once. Thats why singleton pattern is used here.
 *
 * @author İ. BAŞAR YARGICI
 */
public final class DataSourceUtil {

    private static DataSourceUtil INSTANCE = null;
    private static DataSource dataSource = null;
    private static Connection connection;

    private DataSourceUtil() {
        try {
            Context ctx = new InitialContext();
            // You must write the database you will use. Here we use "sample" built-in database.
            dataSource = (DataSource) ctx.lookup("jdbc/sample");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static DataSourceUtil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataSourceUtil();
        }

        return INSTANCE;
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = dataSource.getConnection();
        }
        return connection;
    }

    public static DataSource getDataSource() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }
        return dataSource;
    }
}
