package com.spring.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Error {
    private int statusCode;

    private String message;

    private Long timeStamp;
}
