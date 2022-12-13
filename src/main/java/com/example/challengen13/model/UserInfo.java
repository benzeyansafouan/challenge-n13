package com.example.challengen13.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * This class represents a user information in the system.
 */
@Document("userInfo")
public class UserInfo {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String arabicFirstName;
    private String arabicLastName;
    private String cin;
    private String profession;
    private Date birthDate;
    private CardType cardType;
    private String imageFileObjectId;

    public UserInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getArabicFirstName() {
        return arabicFirstName;
    }

    public void setArabicFirstName(String arabicFirstName) {
        this.arabicFirstName = arabicFirstName;
    }

    public String getArabicLastName() {
        return arabicLastName;
    }

    public void setArabicLastName(String arabicLastName) {
        this.arabicLastName = arabicLastName;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getImageFileObjectId() {
        return imageFileObjectId;
    }

    public void setImageFileObjectId(String imageFileObjectId) {
        this.imageFileObjectId = imageFileObjectId;
    }
}
