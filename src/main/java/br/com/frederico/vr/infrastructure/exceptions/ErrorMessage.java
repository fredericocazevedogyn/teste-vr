package br.com.frederico.vr.infrastructure.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
@AllArgsConstructor
public class ErrorMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date timestamp;
    private String message;
    private String details;

}
