package com.example.moneyTransferApp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CustomError {
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "Сущность с этим id не найден", "Не найдено"),
    ENTITY_DELETED(HttpStatus.GONE, "Сущность с этим id был удален", "Удалено"),
    ENTITY_BAD_DATA(HttpStatus.BAD_REQUEST, "Неправильные данные запроса", "Неправильный запрос"),

    AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, "Неправильный логин или пароль", "Не удалось авторизоваться"),
    NOT_AUTHORIZED(HttpStatus.UNAUTHORIZED, "Пользователь не авторизован", "Не авторизован"),
    TOKEN_IS_EXPIRED(HttpStatus.UNAUTHORIZED, "Время авторизации закончилось", "Пройдите авторизацию"),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "Недействительная авторизация", "Пройдите авторизацию"),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found", "User not found"),
    USER_FOUND(HttpStatus.FOUND, "Такой почта уже существует", "Введите другой email");


    final HttpStatus status;
    final String message;
    final String title;

}
