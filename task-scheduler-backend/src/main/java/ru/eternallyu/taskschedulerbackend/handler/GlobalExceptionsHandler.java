package ru.eternallyu.taskschedulerbackend.handler;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.eternallyu.taskschedulerbackend.exception.AlreadyExistsException;
import ru.eternallyu.taskschedulerbackend.exception.NotFoundException;
import ru.eternallyu.taskschedulerbackend.exception.UserAuthenticationException;
import ru.eternallyu.taskschedulerbackend.service.dto.ErrorDto;

@RestControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorDto handleAlreadyExistsException(AlreadyExistsException alreadyExistsException) {
        return new ErrorDto(alreadyExistsException.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorDto handleNotFoundException(NotFoundException notFoundException) {
        return new ErrorDto(notFoundException.getMessage());
    }

    @ExceptionHandler(UserAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ErrorDto handleUserAuthenticationException(UserAuthenticationException userAuthenticationException) {
        return new ErrorDto(userAuthenticationException.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorDto handleIllegalArgumentException() {
        return new ErrorDto("Invalid input");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorDto handleException() {
        return new ErrorDto("Unknown server error");
    }

    @ExceptionHandler(JWTCreationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorDto handleJWTCreationException() {
        return new ErrorDto("Unknown server error");
    }

    @ExceptionHandler(JWTVerificationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorDto handleJWTVerificationException() {
        return new ErrorDto("Invalid JWT token");
    }
}
