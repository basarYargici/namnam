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

import domain.VisitorDomain;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Result;
import model.Visitor;

/**
 *
 * @author İ. BAŞAR YARGICI
 */
@ManagedBean(name = "login")
@SessionScoped
public class LoginController {
    private String username;
    private String password;

    private final VisitorDomain visitorDomain;

    public LoginController() {
        this.visitorDomain = new VisitorDomain();
    }

    public LoginController(VisitorDomain visitorDomain) {
        this.visitorDomain = visitorDomain;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    /**
     * Check isSuccess in UI. if it is false, pop up the message. You can get
     * data in HTML with login.getById(id).data
     *
     * @param username
     * @param password
     * @return Success if no error occurs, with data is Visitor with id =: id .
     * Error if any error occurs, with error message.
     */
    public Result signIn() {
        Visitor temp = new Visitor();
        temp.setUsername(getUsername());
        temp.setPassword(getPassword());

        return visitorDomain.getByCredentials(temp);
    }

    /**
     * Check isSuccess in UI. if it is false, pop up the message. You can get
     * data in HTML with login.getById(id).data
     *
     * @param name
     * @param surname
     * @param mail
     * @param username
     * @param password
     * @return Success if no error occurs, with data is Visitor with id =: id .
     * Error if any error occurs, with error message.
     */
    private String name;
    private String surname;
    private String mail;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMail() {
        return mail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public Result signUp() {
        Visitor temp = new Visitor();
        temp.setName(getName());
        temp.setSurname(getSurname());
        temp.setMail(getMail());
        temp.setUsername(getUsername());
        temp.setPassword(getPassword());

        return visitorDomain.save(temp);
    }
}
