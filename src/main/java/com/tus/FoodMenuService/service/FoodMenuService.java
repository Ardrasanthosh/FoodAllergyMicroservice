package com.tus.FoodMenuService.service;

import com.tus.FoodMenuService.model.FoodAllergensResponse;
import com.tus.FoodMenuService.model.FoodMenuRequest;
import com.tus.FoodMenuService.model.FoodMenuResponse;

public interface FoodMenuService 
{
    long addFoodItem(FoodMenuRequest foodMenuRequest);

    FoodMenuResponse getFoodItemByID(long foodID);

    FoodAllergensResponse getFoodAllergensByID(long foodID);
}
