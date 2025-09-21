package com.github.bancosil.config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppConstants {

    public static final String ACCOUNT_BASE_PATH = "/accounts";
    public static final String CREATE_PATH = "/create";
    public static final String ID_PATH = "/{id}";
    public static final String SEARCH_PATH = "/search";
    public static final String DELETE_PATH = "/{id}";

    public static final String AUTH_BASE_PATH = "/auth";
    public static final String LOGIN_PATH = "/login";
    public static final String LOGOUT_PATH = "/logout";

    public static final String OPERATION_BASE_PATH = "/operation";
    public static final String DEPOSIT_PATH = "/deposit";
    public static final String WITHDRAW_PATH = "/withdraw";
    public static final String TRANSFER_PATH = "/transfer";

    public static final String USERNAME_PARAM = "username";

    public static final String ACCOUNT_DELETED_MSG = "A conta %s foi deletada";
    public static final String DEPOSIT_MSG = "Deposito de R$%.2f realizado com sucesso";
    public static final String WITHDRAW_MSG = "Saque de R$%.2f realizado com sucesso";
    public static final String TRANSFER_MSG = "Transferência de R$%.2f para %s realizada com sucesso";



}
