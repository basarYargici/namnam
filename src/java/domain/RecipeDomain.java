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
 *
 * @author İ. BAŞAR YARGICI
 */
public class RecipeDomain extends BaseDomain {

    CachedRowSet rowSet = null;
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
    int temp = 0;

    public Result save(Recipe recipe) {
        if (dataSourceResult.isSuccess == false) {
            return dataSourceResult;
        }
        if (connectionResult.isSuccess == false) {
            return connectionResult;
        }

        query = "INSERT INTO APP.RECIPE"
                + "(recipe.category_id,recipe.DESCRIPTION,recipe.\"NAME\")"
                + "VALUES (3, '    Uygun bir tencereye, vanilya ve tereyağı hariç puding için gerekli olan diğer malzemeleri alalım.\n"
                + "    Orta ateşte, bir çırpma teli yardımıyla devamlı karıştırarak pişirmeye başlayalım.\n"
                + "    Kıvam alıp, göz göz olmaya başlayınca birkaç dakika daha karıştırarak pişirelim.\n"
                + "    Ateşten aldığımız pudingin içine vanilya ve tereyağı ilavesini yaparak, mikserle bir kaç dakika çırpalım. Burada tel çırpıcı da kullanabilirsiniz ancak mikser ile kıvamı daha güzel olacaktır.\n"
                + "    Hazır olan pudingi kepçe yardımı ile kaselere aktaralım. Ben kullandığım kaseler ile 4 kase elde ettim.\n"
                + "    Oda sıcaklığına gelen pudingimizi buzdolabına kaldırarak bir kaç saat dinlenmeye bırakalım.\n"
                + "    Güzelce dinlenen ve soğuyan pudinglerimizi dilediğimiz gibi süsleyerek servis edelim. Afiyet olsun.', 'Kakaolu Puding')";

        try (Statement statement = connectionResult.data.createStatement()) {
            if (temp == 0) {
                temp++;
                return new Error("Error");
            }
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
     * @return Success if no error occurs, with data of List of Recipes. Error
     * if any error occurs, with error message.
     */
    public Result getAll() {
        if (dataSourceResult.isSuccess == false) {
            return dataSourceResult;
        }
        if (connectionResult.isSuccess == false) {
            return connectionResult;
        }

        query = "SELECT r.*, c.IMAGE_LINK "
                + "FROM RECIPE r, CATEGORY c "
                + "WHERE r.CATEGORY_ID = c.ID";

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

    public Result getRandom() { //yeni eklenenler sayfasında kullanılacak
        if (dataSourceResult.isSuccess == false) {
            return dataSourceResult;
        }
        if (connectionResult.isSuccess == false) {
            return connectionResult;
        }

        // Select top 5 randrom        
        query = "SELECT r.*, c.IMAGE_LINK "
                + "FROM RECIPE r, CATEGORY c "
                + "WHERE r.CATEGORY_ID = c.ID "
                + "ORDER BY RANDOM() OFFSET 0 ROWS FETCH NEXT 5 ROW ONLY";

        try (Statement statement = connectionResult.data.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            Recipe temp;
            recipeList.clear();

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
     * data in HTML with recipe.all.data
     *
     * @return Success if no error occurs, with data of List of Recipes. Error
     * if any error occurs, with error message.
     */
    public Result getPopular() { //trends sayfasında kullanılacak
        if (dataSourceResult.isSuccess == false) {
            return dataSourceResult;
        }
        if (connectionResult.isSuccess == false) {
            return connectionResult;
        }

        query = "SELECT r.*, c.IMAGE_LINK "
                + "FROM RECIPE r, CATEGORY c "
                + "WHERE r.CATEGORY_ID = c.ID AND SCORE>=4  "
                + "ORDER BY date_of_creation DESC "
                + "FETCH FIRST 10 ROWS ONLY";

        try (Statement statement = connectionResult.data.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            Recipe temp;
            recipeList.clear();

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
     * data in HTML with recipe.all.data
     *
     *
     *
     * @param id
     * @return Success if no error occurs, with data of List of Recipes. Error
     * if any error occurs, with error message.
     */
    public Result getDailyMenu(int id) {
        if (dataSourceResult.isSuccess == false) {
            return dataSourceResult;
        }
        if (connectionResult.isSuccess == false) {
            return connectionResult;
        }

        // TODO: Select one recipe for each category
        query = "SELECT r.*, c.IMAGE_LINK "
                + "FROM RECIPE r, CATEGORY c "
                + "WHERE r.CATEGORY_ID = c.ID and r.id=" + id;

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
     * @return Success if no error occurs, with data is Recipe with id =: id .
     * Error if any error occurs, with error message.
     */
    public Result getById(int id) {
        if (dataSourceResult.isSuccess == false) {
            return dataSourceResult;
        }
        if (connectionResult.isSuccess == false) {
            return connectionResult;
        }

        query = "SELECT r.*, c.IMAGE_LINK "
                + "FROM RECIPE r, CATEGORY c "
                + "WHERE r.CATEGORY_ID = c.ID "
                + "AND r.ID = " + id;

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

    public Result getByName(String name) {
        if (dataSourceResult.isSuccess == false) {
            return dataSourceResult;
        }
        if (connectionResult.isSuccess == false) {
            return connectionResult;
        }

        query = "SELECT r.*, c.IMAGE_LINK "
                + "FROM RECIPE r, CATEGORY c "
                + "WHERE r.CATEGORY_ID = c.ID "
                + "and r.\"NAME\" like" + "'%" + name + "%'";

        try (Statement statement = connectionResult.data.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            Recipe temp;
            recipeList.clear();
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
     * @return Success if no error occurs, with data is Recipe with id =: id .
     * Error if any error occurs, with error message.
     */
    public Result getLatestList() {
        if (dataSourceResult.isSuccess == false) {
            return dataSourceResult;
        }
        if (connectionResult.isSuccess == false) {
            return connectionResult;
        }

        // Select top 10 latest recipe 
        query = "SELECT r.*, c.IMAGE_LINK "
                + "FROM RECIPE r, CATEGORY c "
                + "WHERE r.CATEGORY_ID = c.ID "
                + "ORDER BY date_of_creation DESC "
                + "FETCH FIRST 10 ROWS ONLY";

        try (Statement statement = connectionResult.data.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            Recipe temp;
            recipeList.clear();

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

        query = "select recipe.*, category.image_link "
                + "from recipe "
                + "inner join category on category.ID = recipe.CATEGORY_ID "
                + "where category.ID=" + id;

        try (Statement statement = connectionResult.data.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            Recipe temp;
            recipeList.clear();
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
        temp.setDateOfCreation(rs.getDate("DATE_OF_CREATION"));
        temp.setName(rs.getString("NAME"));
        // image comes from category
        temp.setImageLink(rs.getString("IMAGE_LINK"));
        temp.setCategoryNo(rs.getInt("CATEGORY_ID"));
        temp.setUserId(rs.getInt("USER_ID"));
    }
}
