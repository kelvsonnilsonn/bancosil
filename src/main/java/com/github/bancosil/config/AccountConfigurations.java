package com.github.bancosil.config;

import com.github.bancosil.exception.account.UnauthorizedException;
import com.github.bancosil.model.Account;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *  ISSO É TEMPORÁRIO.
 *  EM BREVE USAREI JWT
 */


@Component
public class AccountConfigurations {
    @Getter
    private Account currentUser = null;

    @Autowired
    private LoginSystem system;

    public void logout(){
        if(!isLogged()){
            throw new UnauthorizedException();
        }
        currentUser = null;
    }

    public boolean isLogged(){
        return currentUser != null;
    }

    public void login(String username, String password){
        currentUser = system.login(username, password);
    }

}
