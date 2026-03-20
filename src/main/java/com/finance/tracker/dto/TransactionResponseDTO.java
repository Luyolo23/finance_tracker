package com.finance.tracker.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDTO {

    private Long id;
    private double amount;
    private String type;
    private String categoryName;
}
