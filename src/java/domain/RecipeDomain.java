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
import model.Recipe;
import model.Result;
import model.Success;

/**
 * //TODO: May dateOfCreation be a problem in queries, should be checked.
 *
 * @author İ. BAŞAR YARGICI
 */
public class RecipeDomain extends BaseDomain {

    CachedRowSet rowSet = null;
    Result s;
    Success success;
    Error error;
    ArrayList<Recipe> recipeList;
    String query;
    CategoryDomain categoryDomain;

    public RecipeDomain() {
        this.recipeList = new ArrayList();
        this.categoryDomain = new CategoryDomain();
    }

    /**
     * Check isSuccess in UI. if it is false, pop up the message.
     *
     * @param recipe
     * @return Success if no error occurs, with no extra data. Error if any
     * error occurs, with error message.
     */
    public Result save(Recipe recipe) {
        if (dataSourceResult.isSuccess == false) {
            return dataSourceResult;
        }
        if (connectionResult.isSuccess == false) {
            return connectionResult;
        }

        query = "INSERT INTO APP.RECIPE"
                + "(ID,SCORE,DESCRIPTION,DATEOFCREATION,\"NAME\")"
                + "VALUES (" + recipe.getId() + ",'" + recipe.getScore() + "',"
                + "'" + recipe.getDescription() + "','" + recipe.getDateOfCreation() + "',"
                + "'" + recipe.getName() + "')";

        try (Statement statement = connectionResult.data.createStatement()) {
            statement.executeUpdate(query);
            return new Success();
        } catch (SQLException e) {
            return new Error(e.getMessage());
        }
    }

    /**
     * Check isSuccess in UI. if it is false, pop up the message. You can get
     * data in HTML with recipe.all.data
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

        query = "SELECT * FROM APP.RECIPE";

        try (Statement statement = connectionResult.data.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            Recipe temp;

            while (rs.next()) {
                temp = new Recipe();
                toRecipe(temp, rs);
                recipeList.add(temp);
            }
            return new Success(recipeList);
        } catch (SQLException e) {
            return new Error(e.getMessage());
        }

    }

    /**
     * Check isSuccess in UI. if it is false, pop up the message. You can get
     * data in HTML with recipe.getById(id).data
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

        query = "SELECT * FROM APP.RECIPE WHERE ID =" + id;

        try (Statement statement = connectionResult.data.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            Recipe temp = new Recipe();

            while (rs.next()) {
                toRecipe(temp, rs);
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
     * @param recipe
     * @return Success if no error occurs, with no extra data. Error if any
     * error occurs, with error message.
     */
    public Result update(Recipe recipe) {
        if (dataSourceResult.isSuccess == false) {
            return dataSourceResult;
        }
        if (connectionResult.isSuccess == false) {
            return connectionResult;
        }

        query = "UPDATE APP.RECIPE "
                + "SET SCORE = '" + recipe.getScore() + "', DESCRIPTION = "
                + "'" + recipe.getDescription() + "', DATEOFCREATION = '"
                + recipe.getDateOfCreation() + "', \"NAME\" = " + "'"
                + recipe.getName() + "' WHERE id = " + recipe.getId();

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

        query = "DELETE FROM APP.VISITOR WHERE id = " + id;

        try (Statement statement = connectionResult.data.createStatement()) {
            statement.executeUpdate(query);
            statement.close();
            return new Success();
        } catch (SQLException e) {
            return new Error(e.getMessage());
        }
    }

    public Result getAllByCategoryId(int id) {
        if (dataSourceResult.isSuccess == false) {
            return dataSourceResult;
        }
        if (connectionResult.isSuccess == false) {
            return connectionResult;
        }

        query = "SELECT * FROM APP.RECIPE WHERE CATEGORY_ID = " + id;

        try (Statement statement = connectionResult.data.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            Recipe temp;

            while (rs.next()) {
                temp = new Recipe();
                toRecipe(temp, rs);
                recipeList.add(temp);
            }
            return new Success(recipeList);
        } catch (SQLException e) {
            return new Error(e.getMessage());
        }
    }

    private void toRecipe(Recipe temp, ResultSet rs) throws SQLException {
        temp.setId(rs.getInt("ID"));
        temp.setScore(rs.getString("SCORE"));
        temp.setDescription(rs.getString("DESCRIPTION"));
        temp.setDateOfCreation(rs.getDate("DATEOFCREATION"));
        temp.setName(rs.getString("NAME"));
    }

}
