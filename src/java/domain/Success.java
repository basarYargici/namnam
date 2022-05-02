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

/**
 *
 * @author İ. BAŞAR YARGICI
 */
public class Success<T> extends Result {

    public Success() {
        isSuccess = true;
    }

    public Success(T data) {
        isSuccess = true;
        this.data = data;
    }

    public Success(String message, T data) {
        isSuccess = true;
        this.message = "[Success] :" + message;
        this.data = data;
    }

    public boolean isIsSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

}
