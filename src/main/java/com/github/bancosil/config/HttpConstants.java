package com.github.bancosil.config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HttpConstants {
    public static final String OK = "200";
    public static final String CREATED = "201";
    public static final String NO_CONTENT = "204";
    public static final String BAD_REQUEST = "400";
    public static final String UNAUTHORIZED = "401";
    public static final String NOT_FOUND = "404";
    public static final String CONFLICT = "409";
    public static final String SERVER_ERROR = "500";

    public static final String UNAUTHORIZED_MSG = "Sem permissão";
    public static final String BAD_REQUEST_MSG = "Informações inseridas inválidas.";
    public static final String INTERN_SERVER_ERROR_MSG = "Erro interno do servidor";
    public static final String NOT_FOUND_MSG = "Informação não encontrada";
    public static final String NO_CONTENT_MSG = "Não há conteúdo a ser mostrado";
}
