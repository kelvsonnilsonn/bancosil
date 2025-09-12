package com.github.bancosil.config;

import com.github.bancosil.model.Account;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountConfigurations {
    @Getter
    private Account currentUser = null;

    @Autowired
    private LoginSystem system;

    public void logout(){
        this.currentUser = null;
    }

    public boolean isLogged(){
        return currentUser != null;
    }

    public void login(String username, String password){
        this.currentUser = system.login(username, password);
    }

}
