package com.finance.tracker.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private int status;
}
