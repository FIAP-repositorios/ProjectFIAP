package br.com.projectgro.exceptions;

import java.sql.SQLException;

public class InternalServerErrorException extends SQLException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}
