package com.example.demo.entities.sets;

import lombok.Data;

/**
 * Базовые характеристики всех заказов
 */
@Data
public class OrderBaseSet {

    public Double x; // формат в мм, по X
    public Double y; // формат в мм, по Y
    public String type; // тип изделия
    public int amount; // тираж (кол-во)

}
