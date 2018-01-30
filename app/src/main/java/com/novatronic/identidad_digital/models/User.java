package com.novatronic.identidad_digital.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
@Entity( tableName = "user", indices = {@Index(value = {"document", "pin"})})
public class User {
    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "document")
    private String document;

    @ColumnInfo(name = "pin")
    private String pin;

    //@ColumnInfo(name = "fingerprint")
    @Ignore
    private  Byte[] fingerprint;

    @ColumnInfo(name = "document_kind")
    private String document_kind;

    @ColumnInfo(name = "country")
    private String country;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Byte[] getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(Byte[] fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getDocument_kind() {
        return document_kind;
    }

    public void setDocument_kind(String document_kind) {
        this.document_kind = document_kind;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}