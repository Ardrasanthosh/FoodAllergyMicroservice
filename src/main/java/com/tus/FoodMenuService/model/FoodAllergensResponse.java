package com.tus.FoodMenuService.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodAllergensResponse
{
    private long foodID;
    private String foodName;
    private String foodAllergens;
}
