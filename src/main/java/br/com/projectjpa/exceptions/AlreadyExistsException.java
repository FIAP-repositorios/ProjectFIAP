package br.com.projectjpa.exceptions;

import java.sql.SQLException;

public class AlreadyExistsException  extends SQLException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
