package com.github.bancosil.model.valueobjects;

import com.github.bancosil.enums.UserRole;
import com.github.bancosil.model.valueobjects.account.Email;
import com.github.bancosil.model.valueobjects.account.Password;
import com.github.bancosil.model.valueobjects.account.Username;
import com.github.bancosil.model.valueobjects.cpfchecker.CPF;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class UserInformation {
    @Embedded
    private Username username;

    @Embedded
    private Password password;

    @Embedded
    private Email email;

    @Embedded
    private CPF cpf;

    @Getter
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public UserInformation(Username username, Password password, Email email, CPF cpf){
        this.username = username;
        this.password = password;
        this.email = email;
        this.cpf = cpf;
    }

    public String getUsername(){
        return username.getUsername();
    }

    public String getEmail(){
        return email.getEmail();
    }

    public String getCpf(){ return cpf.getCpf(); }

    public String getPassword(){
        return password.getPassword();
    }
}
