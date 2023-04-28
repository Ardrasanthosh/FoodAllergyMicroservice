package com.tus.FoodMenuService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodMenuRequest
{
    private String foodName;
    private String foodType;
    private String foodAllergens;
    private double foodPrice;
}
