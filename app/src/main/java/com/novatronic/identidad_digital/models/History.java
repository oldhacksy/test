package com.novatronic.identidad_digital.models;





import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "history")
public class History {


    @DatabaseField(columnName = "id",generatedId = true)
    private int id;


    @DatabaseField(columnName = "dniOrigin")
    private String dniOrigin;

    @DatabaseField(columnName = "dniDestiny")
    private String dniDestiny;

    @DatabaseField(columnName = "operation")
    private String operation;

    @DatabaseField(columnName = "quantity")
    private Integer quantity;
}