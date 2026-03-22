package com.finance.tracker.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SummaryDTO {

    private double totalIncome;
    private double totalExpense;
    private double balance;
}
