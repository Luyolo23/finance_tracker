package com.finance.tracker.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDTO {

    private Long userId;
    private Long categoryId;
    private double amount;
    private String type;
}
