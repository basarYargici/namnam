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

/**
 *
 * @author İ. BAŞAR YARGICI
 */
@ManagedBean(name = "visitor")
@SessionScoped
public class VisitorController {

    private final VisitorDomain visitorDomain;

    public VisitorController() {
        this.visitorDomain = new VisitorDomain();
    }

    public VisitorController(VisitorDomain visitorDomain) {
        this.visitorDomain = visitorDomain;
    }

    /**
     * Check isSuccess in UI. if it is false, pop up the message.
     *
     * This method should be called when categories tab is clicked.
     *
     * @return
     */
    public Result getAll() {
        return visitorDomain.getAll();
    }

    /**
     * Check isSuccess in UI. if it is false, pop up the message.
     *
     * This method should be called when specific category is requested.
     *
     * @param id
     * @return
     */
    public Result getById(int id) {
        return visitorDomain.getById(id);
    }
}
