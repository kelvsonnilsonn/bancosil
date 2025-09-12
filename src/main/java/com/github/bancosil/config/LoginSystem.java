package com.github.bancosil.config;

import com.github.bancosil.model.Account;

public interface LoginSystem {
    Account login(String username, String password);
}
