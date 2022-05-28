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
package model;

import java.util.Date;

/**
 *
 * @author İ. BAŞAR YARGICI
 */
public class Recipe {
    private int id;
    private String name;
    private String description;
    private Date dateOfCreation;
    private String score;
    private int categoryNo;
    private int userId; 
    private String imageLink;

    public Recipe(int id, String name, String description, Date dateOfCreation, String score, int categoryNo, int userId,String imageLink) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateOfCreation = dateOfCreation;
        this.score = score;
        this.categoryNo = categoryNo;
        this.userId = userId;
        this.imageLink = imageLink;
    }

    public Recipe() {   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    
}
