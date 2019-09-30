package com.fcv.expressCourier.payload;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String nameOrEmail;

    @NotBlank
    private String password;

    public String getNameOrEmail() {
        return nameOrEmail;
    }

    public void setNameOrEmail(String nameOrEmail) {
        this.nameOrEmail = nameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
