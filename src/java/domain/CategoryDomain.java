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
import model.Category;
import model.Error;
import model.Result;
import model.Success;

/**
 *
 * @author İ. BAŞAR YARGICI
 */
public class CategoryDomain extends BaseDomain {

    CachedRowSet rowSet = null;
    Result s;
    Success success;
    Error error;
    ArrayList<Category> categoryList;
    String query;

    public CategoryDomain() {
        this.categoryList = new ArrayList();
    }

    /**
     * Check isSuccess in UI. if it is false, pop up the message. I have tested
     * the method in code. It works as expected, but do not know how can we use
     * in HTML. Should check.
     *
     * @param category
     * @return Success if no error occurs, with no extra data. Error if any
     * error occurs, with error message.
     */
    public Result save(Category category) {
        if (dataSourceResult.isSuccess == false) {
            return dataSourceResult;
        }
        if (connectionResult.isSuccess == false) {
            return connectionResult;
        }

        query = "INSERT INTO APP.CATEGORY"
                + "(ID,\"NAME\",IMAGE_LINK)"
                + "VALUES (" + category.getId() + ",'" + category.getName()
                + "', '" + category.getImageLink() + "')";

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

        query = "SELECT * FROM APP.CATEGORY";

        try (Statement statement = connectionResult.data.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            Category temp;
            categoryList.clear();

            while (rs.next()) {
                temp = new Category();
                toCategory(temp, rs);
                categoryList.add(temp);
            }
            return new Success(categoryList);
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
            return dataSourceResult;
        }
        if (connectionResult.isSuccess == false) {
            return connectionResult;
        }

        query = "SELECT * FROM APP.CATEGORY WHERE ID =" + id;

        try (Statement statement = connectionResult.data.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            Category temp = new Category();

            while (rs.next()) {
                toCategory(temp, rs);
            }
            return new Success(temp);
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
     * @param category
     * @return Success if no error occurs, with no extra data. Error if any
     * error occurs, with error message.
     */
    public Result update(Category category) {
        if (dataSourceResult.isSuccess == false) {
            return dataSourceResult;
        }
        if (connectionResult.isSuccess == false) {
            return connectionResult;
        }

        query = "UPDATE APP.CATEGPRY "
                + "SET \"NAME\" = '" + category.getName() + "', IMAGE_LINK = "
                + "'" + category.getImageLink() + "' WHERE id = " + category.getId();

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
     * @param id
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

        query = "DELETE FROM APP.CATEGPRY WHERE id = " + id;

        try (Statement statement = connectionResult.data.createStatement()) {
            statement.executeUpdate(query);
            statement.close();
            return new Success();
        } catch (SQLException e) {
            return new Error(e.getMessage());
        }
    }

    private void toCategory(Category temp, ResultSet rs) throws SQLException {
        temp.setId(rs.getInt("ID"));
        temp.setImageLink(rs.getString("IMAGE_LINK"));
        temp.setName(rs.getString("NAME"));
    }

}
