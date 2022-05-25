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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import model.Result;
import model.Visitor;

/**
 *
 * @author İ. BAŞAR YARGICI
 */
@ManagedBean(name = "login")
@SessionScoped
public class LoginController {
    
    private Visitor visitor;
    private String error_message;

    public Visitor getVisitor() {
        if (this.visitor == null){
            this.visitor = new Visitor();
        }
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
    
    
    
    private final VisitorDomain visitorDomain;

    public LoginController() {
        this.visitorDomain = new VisitorDomain();
    }

    public LoginController(VisitorDomain visitorDomain) {
        this.visitorDomain = visitorDomain;
    }


    
    public String login(){
        
        
        if(this.visitor.getUsername().equals("kullanici") && this.visitor.getPassword().equals("parola123")){
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("valid_user", this.visitor);
            setError_message("Giris Basarili");
            return "/recipe";
        }else{
            setError_message("Hatali kullanici adi veya sifre");
            return "/login";
        }
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
    public Result signIn(String username, String password) {
        Visitor temp = new Visitor();
        temp.setUsername(username);
        temp.setPassword(password);

        return visitorDomain.getByCredentials(temp);
    }
    
    public boolean validatePassword(FacesContext context, UIComponent cmp, Object value) throws ValidatorException {
        
        String v = (String) value;
        
        if(v.isEmpty()){
            throw new ValidatorException(new FacesMessage("Sifre alani bos olamaz!"));
        }else if(v.length() < 8){
            throw new ValidatorException(new FacesMessage("Sifre alani 8 karakterden kisa olamaz!"));
        }
        return true;
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
   
    
    public Result signUp(String name, String surname, String mail, String username, String password) {
        Visitor temp = new Visitor();
        temp.setName(name);
        temp.setSurname(surname);
        temp.setMail(mail);
        temp.setUsername(username);
        temp.setPassword(password);

        return visitorDomain.save(temp);
    }
}
