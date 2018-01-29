package com.novatronic.identidad_digital.models;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "points")
public class Point {


    @DatabaseField(columnName = "id",generatedId = true)
    private int id;


    @DatabaseField(columnName = "money")
    private Integer money;

    @DatabaseField(columnName = "dni")
    private String dni;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}