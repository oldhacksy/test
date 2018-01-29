package com.novatronic.identidad_digital.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "users")
public class User implements Serializable {


    @DatabaseField(columnName = "id",generatedId = true)
    private int id;

    @DatabaseField(columnName = "first_name")
    private String firstName;

    @DatabaseField(columnName = "last_name_p")
    private String lastNameP;
    @DatabaseField(columnName = "last_name_m")
    private String lastNameM;
    @DatabaseField(columnName = "dni")
    private String dni;

    @DatabaseField(columnName = "pin")
    private String pin;
    @DatabaseField(columnName = "kind")
    private String kind;
    @DatabaseField(columnName = "amount")
    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastNameP() {
        return lastNameP;
    }

    public void setLastNameP(String lastName) {
        this.lastNameP = lastName;
    }
    public String getLastNameM() {
        return lastNameM;
    }

    public void setLastNameM(String lastName) {
        this.lastNameM = lastName;
    }
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}