package com.tus.FoodMenuService.repository;

import com.tus.FoodMenuService.entity.FoodAllergens;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodAllergensRepository extends JpaRepository<FoodAllergens, Long>
{
    Optional<FoodAllergens> findByfoodName(String foodAllergens);
}
