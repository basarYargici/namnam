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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Error;
import model.Result;
import model.Success;
import model.Visitor;

/**
 *
 * @author İ. BAŞAR YARGICI
 */
public class LoginDomain extends BaseDomain {

    private boolean isVisitorLoggedIn = false;
    private String query;
    private final VisitorDomain visitorDomain;

    public LoginDomain(VisitorDomain visitorDomain) {
        this.visitorDomain = visitorDomain;
    }

    /**
     * Check isSuccess in UI. if it is false, pop up the message.
     *
     * @param visitorId
     * @return Success if no error occurs, with boolean logged in data. Error if
     * any error occurs, with error message.
     */
    public Result isLoggedIn(int visitorId) {
        if (dataSourceResult.isSuccess == false) {
            return dataSourceResult;
        }
        if (connectionResult.isSuccess == false) {
            return connectionResult;
        }

        query = "SELECT IS_LOGGED_IN FROM APP.VISITOR WHERE ID = " + visitorId;

        try (Statement statement = connectionResult.data.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                checkLoggedInField(rs);
            }

            return new Success(isVisitorLoggedIn);
        } catch (SQLException e) {
            return new model.Error(e.getMessage());
        }
    }

    private void checkLoggedInField(ResultSet rs) throws SQLException {
        int temp = rs.getInt("ID");
        isVisitorLoggedIn = temp != 0;
    }

    /**
     * Check isSuccess in UI. if it is false, pop up the message.
     *
     * @param visitor
     * @return Success if no error occurs, with boolean logged in data. Error if
     * any error occurs, with error message.
     */
    public Result signIn(Visitor visitor) {
        if (dataSourceResult.isSuccess == false) {
            return new Error("dataSourceResult is null");
        }
        if (connectionResult.isSuccess == false) {
            return new Error("connectionResult is null");
        }

        Result dbVisitorResult = visitorDomain.getByCredentials(visitor);
        return setVisitorLoggedIn(dbVisitorResult);
    }

    /**
     * Check isSuccess in UI. if it is false, pop up the message.
     *
     * @param visitor
     * @return Success if no error occurs, with boolean logged in data. Error if
     * any error occurs, with error message.
     */
    public Result signUp(Visitor visitor) {
        if (dataSourceResult.isSuccess == false) {
            return new Error("dataSourceResult is null");
        }
        if (connectionResult.isSuccess == false) {
            return new Error("connectionResult is null");
        }

        if (visitor.getUsername() != null && visitor.getPassword() != null) {
            Result dbVisitorResult = visitorDomain.save(visitor);
            return setVisitorLoggedIn(dbVisitorResult);
        }
        return new Error();
    }

    private Result setVisitorLoggedIn(Result dbVisitorResult) {
        if (dbVisitorResult.isSuccess) {
            Visitor temp = (Visitor) dbVisitorResult.data;
            query = "UPDATE APP.VISITOR SET IS_LOGGED_IN = 1 WHERE ID = " + temp.getId();

            try (Statement statement = connectionResult.data.createStatement()) {
                statement.executeUpdate(query);
                return new Success(true);
            } catch (SQLException e) {
                return new Error(e.getMessage());
            }
        }
        return new Error(dbVisitorResult.message);
    }
}
