package com.example.demo.entities.sets;

import lombok.Data;

/**
 *  Набор параметров, описывающих изделие Брошюра
 */

@Data
public class VarOfBrochureSetOrder extends OrderBaseSet {

    public String orient; // вертикальная или горизонтальная
    public int amBlock; // кол-во полос блок
    public int amCover; // кол-во полос обложка
    public String paperBlock; // бумага в блоке
    public String paperCover; // бумага на обложке
    public String modBlock; // цветовая модель для печати блока
    public String modCover; // цветовая модель для печати обложки
    public String methodOfBounding; // метод переплёта

}
