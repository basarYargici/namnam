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
package controller;

import domain.RecipeDomain;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Recipe;
import model.Result;

/**
 *
 * @author İ. BAŞAR YARGICI
 */
@ManagedBean(name = "recipe")
@SessionScoped
public class RecipeController {

    private final RecipeDomain recipeDomain;
    private final Recipe recipe;
    int tempRecipeId;
    String tempRecipeName;
    public RecipeController() {
        this.recipeDomain = new RecipeDomain();
        this.recipe = new Recipe();
    }

    public RecipeController(RecipeDomain recipeDomain, Recipe recipe) {
        this.recipeDomain = recipeDomain;
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public int getTempRecipeId() {
        return tempRecipeId;
    }

    public void setTempRecipeId(int tempRecipeId) {
        this.tempRecipeId = tempRecipeId;
    }    

    public String getTempRecipeName() {
        return tempRecipeName;
    }

    public void setTempRecipeName(String tempRecipeName) {
        this.tempRecipeName = tempRecipeName;
    }
    
    /**
     * Check isSuccess in UI. if it is false, pop up the message.
     *
     * This method should be called when new recipe create request received.
     *
     * !IMPORTANT PARAM!
     *
     * @param recipe, user id should be the is logged in user. Date might throw
     * exception, should check.
     * @return
     */
    public Result add() {
        return recipeDomain.save(recipe);
    }

    /**
     * Check isSuccess in UI. if it is false, pop up the message.
     *
     * This method should be called when all recipes are requested.
     *
     * @return
     */    
    public Result getAll() {
        return recipeDomain.getAll();
    }

    /**
     * Check isSuccess in UI. if it is false, pop up the message.
     *
     * This method should be called when popular recipes or suggestions are
     * requested.
     *
     * @return
     */
    public Result getRandom() {
        return recipeDomain.getRandom();
    }

    /**
     * Check isSuccess in UI. if it is false, pop up the message.
     *
     * This method should be called when popular recipes or suggestions are
     * requested.
     *
     * @param id
     * @return
     */
    public Result getDailyMenu(int id) {
        return recipeDomain.getDailyMenu( id);
    }

    /**
     * Check isSuccess in UI. if it is false, pop up the message.
     *
     * This method should be called when category detail(Recipes) is requested.
     *
     * @param id
     * @return
     */
    public Result getAllByCategoryId(int id) {
        return recipeDomain.getAllByCategoryId(id);
    }

    /**
     * Check isSuccess in UI. if it is false, pop up the message.
     *
     * This method should be called when latest recipes are requested.
     *
     * @return
     */
    public Result getLatest() {
        return recipeDomain.getLatestList();
    }

    public Result getPopular() {
        return recipeDomain.getPopular();
    }

    public Result getById(int id) {
        return recipeDomain.getById(id);
    }
    public Result getByName(String name){
        return recipeDomain.getByName(name);
    }
}
