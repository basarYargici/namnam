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

import util.DataSourceUtil;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import model.Error;
import model.Result;
import model.Success;

/**
 *
 * @author İ. BAŞAR YARGICI
 */
public class BaseDomain {

    protected Result<DataSource> dataSourceResult = getDataSource();
    protected Result<Connection> connectionResult = getConnection();

    protected Result getDataSource() {
        try {
            return new Success(DataSourceUtil.getInstance().getDataSource());
        } catch (SQLException e) {
            return new Error(e.getLocalizedMessage());
        }
    }

    protected Result<Connection> getConnection() {
        try {
            return new Success(DataSourceUtil.getInstance().getConnection());
        } catch (SQLException e) {
            return new Error(e.getLocalizedMessage());
        }
    }

}
