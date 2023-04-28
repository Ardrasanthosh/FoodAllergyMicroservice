package com.tus.FoodMenuService.repository;

import com.tus.FoodMenuService.entity.FoodMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodMenuRepository extends JpaRepository<FoodMenu, Long>
{

}
