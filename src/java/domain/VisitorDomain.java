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
import java.util.ArrayList;
import javax.sql.rowset.CachedRowSet;
import model.Error;
import model.Result;
import model.Success;
import model.Visitor;

/**
 *
 * @author İ. BAŞAR YARGICI
 */
public class VisitorDomain extends BaseDomain {

    CachedRowSet rowSet = null;
    ArrayList<Visitor> visitorList;
    String query;

    public VisitorDomain() {
        this.visitorList = new ArrayList();
    }

    /**
     * Check isSuccess in UI. if it is false, pop up the message. I have tested
     * the method in code. It works as expected, but do not know how can we use
     * in HTML. Should check.
     *
     * @param visitor
     * @return Success if no error occurs, with no extra data. Error if any
     * error occurs, with error message.
     */
    public Result save(Visitor visitor) {
        if (dataSourceResult.isSuccess == false) {
            return dataSourceResult;
        }
        if (connectionResult.isSuccess == false) {
            return connectionResult;
        }

        query = "INSERT INTO APP.VISITOR"
                + "(USERNAME,PASSWORD,MAIL,FIRSTNAME,LASTNAME)"
                + "VALUES ('" + visitor.getUsername() + "',"
                + "'" + visitor.getPassword() + "','" + visitor.getMail() + "',"
                + "'" + visitor.getName() + "','" + visitor.getSurname() + "')";

        try (Statement statement = connectionResult.data.createStatement()) {
            statement.executeUpdate(query);
            return new Success();
        } catch (SQLException e) {
            return new Error(e.getMessage());
        }
    }

    /**
     * Check isSuccess in UI. if it is false, pop up the message. You can get
     * data in HTML with visitor.all.data
     *
     * @return Success if no error occurs, with data of List of Visitors. Error
     * if any error occurs, with error message.
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

    /**
     * Check isSuccess in UI. if it is false, pop up the message. You can get
     * data in HTML with visitor.getById(id).data
     *
     * @param id
     * @return Success if no error occurs, with data is Visitor with id =: id .
     * Error if any error occurs, with error message.
     */
    public Result getById(int id) {
        if (dataSourceResult.isSuccess == false) {
            return new Error("dataSourceResult is null");
        }
        if (connectionResult.isSuccess == false) {
            return new Error("connectionResult is null");
        }

        query = "SELECT * FROM APP.VISITOR WHERE ID =" + id;

        try (Statement statement = connectionResult.data.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            Visitor visitor = new Visitor();

            while (rs.next()) {
                toVisitor(visitor, rs);
            }
            return new Success(visitor);
        } catch (SQLException e) {
            return new Error(e.getMessage());
        }
    }

    /**
     * Check isSuccess in UI. if it is false, pop up the message.
     *
     * I have tested the method in code. It works as expected, but do not know
     * how can we use in HTML. Should check.
     *
     * @param visitor
     * @return Success if no error occurs, with no extra data. Error if any
     * error occurs, with error message.
     */
    public Result update(Visitor visitor) {
        if (dataSourceResult.isSuccess == false) {
            return dataSourceResult;
        }
        if (connectionResult.isSuccess == false) {
            return connectionResult;
        }

        query = "UPDATE APP.VISITOR "
                + "SET USERNAME = '" + visitor.getUsername() + "', PASSWORD = "
                + "'" + visitor.getPassword() + "', MAIL = '" + visitor.getMail() + "', \"NAME\" = "
                + "'" + visitor.getName() + "', SURNAME = '" + visitor.getSurname() + "' "
                + "WHERE id = " + visitor.getId();

        try (Statement statement = connectionResult.data.createStatement()) {
            statement.executeUpdate(query);
            return new Success();
        } catch (SQLException e) {
            return new Error(e.getMessage());
        }
    }

    /**
     * Check isSuccess in UI. if it is false, pop up the message.
     *
     * I have tested the method in code. It works as expected, but do not know
     * how can we use in HTML. Should check.
     *
     * @param visitor
     * @return Success if no error occurs, with no extra data. Error if any
     * error occurs, with error message.
     */
    public Result delete(int id) {
        if (dataSourceResult.isSuccess == false) {
            return dataSourceResult;
        }
        if (connectionResult.isSuccess == false) {
            return connectionResult;
        }

        query = "DELETE FROM APP.VISITOR WHERE id = " + id;

        try (Statement statement = connectionResult.data.createStatement()) {
            statement.executeUpdate(query);
            statement.close();
            return new Success();
        } catch (SQLException e) {
            return new Error(e.getMessage());
        }
    }

    /**
     * Check isSuccess in UI. if it is false, pop up the message. You can get
     * data in HTML with visitor.getById(id).data
     *
     * @param visitor
     * @return Success if no error occurs, with data is Visitor with id =: id .
     * Error if any error occurs, with error message.
     */
    public Result getByCredentials(Visitor visitor) {
        if (dataSourceResult.isSuccess == false) {
            return new Error("dataSourceResult is null");
        }
        if (connectionResult.isSuccess == false) {
            return new Error("connectionResult is null");
        }

        query = "SELECT * FROM APP.VISITOR WHERE USERNAME = '" + visitor.getUsername()
                + "' AND PASSWORD = '" + visitor.getPassword() + "'";

        try (Statement statement = connectionResult.data.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            Visitor temp = new Visitor();

            while (rs.next()) {
                toVisitor(temp, rs);
            }
            if (temp.getUsername() == null) {
                return new Error("Visitor could not found");
            }

            return new Success(temp);
        } catch (SQLException e) {
            return new Error(e.getMessage());
        }
    }

    private void toVisitor(Visitor temp, ResultSet rs) throws SQLException {
        temp.setId(rs.getInt("ID"));
        temp.setUsername(rs.getString("USERNAME"));
        temp.setPassword(rs.getString("PASSWORD"));
        temp.setName(rs.getString("FIRSTNAME"));
        temp.setSurname(rs.getString("LASTNAME"));
        temp.setMail(rs.getString("MAIL"));
    }
}
