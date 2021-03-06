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

import domain.LoginDomain;
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

    private final Visitor visitor;
    private final VisitorDomain visitorDomain;
    private final LoginDomain loginDomain;

    public LoginController() {
        this.visitor = new Visitor();
        this.visitorDomain = new VisitorDomain();
        this.loginDomain = new LoginDomain(visitorDomain);
    }

    public Visitor getVisitor() {
        return visitor;
    }

    /**
     * Check isSuccess in UI. if it is false, pop up the message. You can get
     * data in HTML with login.getById(id).data
     *
     * @return Success if no error occurs, with boolean logged in data. Error if
     * any error occurs, with error message.
     */
    public Result signIn() {
        return loginDomain.signIn(visitor);
    }

    /**
     * Check isSuccess in UI. if it is false, pop up the message. You can get
     * data in HTML with login.getById(id).data
     *
     * @return Success if no error occurs, with boolean logged in data. Error if
     * any error occurs, with error message.
     */
    public Result signUp() {
        return loginDomain.signUp(visitor);
    }
}
