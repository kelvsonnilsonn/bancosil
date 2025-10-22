package com.github.bancosil.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppConstants {

    // Paths

    public static final String ACCOUNT_BASE_PATH = "/accounts";
    public static final String ID_PATH = "/{id}";
    public static final String ADMIN_PATH = "/admin";
    public static final String EVENT_BASE_PATH = "/events";

    public static final String AUTH_BASE_PATH = "/auth";
    public static final String LOGIN_PATH = "/login";
    public static final String REGISTER_PATH = "/register";

    public static final String OPERATION_BASE_PATH = "/operation";
    public static final String DEPOSIT_PATH = "/deposit";
    public static final String WITHDRAW_PATH = "/withdraw";
    public static final String TRANSFER_PATH = "/transfer";

    public static final String EVENTS_IN_INTERVAL_PATH = "/interval";
    public static final String MY_EVENTS_IN_INTERVAL_PATH = "/my-events/interval";
    public static final String USER_EVENTS_IN_INTERVAL_PATH = "/user-events/interval";


    // Parâmetros

    public static final String USERNAME_PARAM = "username";

    // Mensagens

    public static final String ACCOUNT_DELETED_MSG = "A conta %s foi deletada";
    public static final String DEPOSIT_MSG = "Operação de deposito realizada com sucesso";
    public static final String WITHDRAW_MSG = "Operação de saque realizada com sucesso";
    public static final String TRANSFER_MSG = "Operação de transferência realizada com sucesso";

    public static final String PRE_AUTHORIZE_ADMIN_REQUISITION = "hasAuthority('ADMIN_ROLE')";
    public static final String PRE_AUTHORIZE_ALL_REQUISITION = "permitAll()";

    public static final String OPERATION = "OPERATION";
    public static final String ACCOUNT = "ACCOUNT";

}
