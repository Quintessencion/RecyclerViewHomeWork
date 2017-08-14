package com.example.recyclerviewhomework;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pole {
    //Fields
    private int resIdImagePolicyType;
    private int resIdImageNext;
    private int resIdImageShoppingCart;

    private String policyType;
    private String policyNumber;
    private String objectOfInsurance;
    private Date policyStartDate;
    private long validityPeriodPolicy;

    private SimpleDateFormat sdf;

    //Constructor
    public Pole(int resIdImagePolicyType, int resIdImageNext, int resIdImageShoppingCart,
                String policyType, String policyNumber, String objectOfInsurance, String policyStartDate, long validityPeriodPolicy) {

        this.resIdImagePolicyType = resIdImagePolicyType;
        this.resIdImageNext = resIdImageNext;
        this.resIdImageShoppingCart = resIdImageShoppingCart;

        this.policyType = policyType;
        this.policyNumber = policyNumber;
        this.objectOfInsurance = objectOfInsurance;

        sdf = new SimpleDateFormat("dd.MM.yyyy");

        try {
            this.policyStartDate = sdf.parse(policyStartDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.validityPeriodPolicy = validityPeriodPolicy;
    }

    //Functions
    public int getResIdImagePolicyType() {
        return resIdImagePolicyType;
    }

    public void setResIdImagePolicyType(int resIdImagePolicyType) {
        this.resIdImagePolicyType = resIdImagePolicyType;
    }

    public int getResIdImageNext() {
        return resIdImageNext;
    }

    public void setResIdImageNext(int resIdImageNext) {
        this.resIdImageNext = resIdImageNext;
    }

    public int getResIdImageShoppingCart() {
        return resIdImageShoppingCart;
    }

    public void setResIdImageShoppingCart(int resIdImageShoppingCart) {
        this.resIdImageShoppingCart = resIdImageShoppingCart;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getObjectOfInsurance() {
        return objectOfInsurance;
    }

    public void setObjectOfInsurance(String objectOfInsurance) {
        this.objectOfInsurance = objectOfInsurance;
    }

    public Date getPolicyStartDate() {
        return policyStartDate;
    }

    public void setPolicyStartDate(Date policyStartDate) {
        this.policyStartDate = policyStartDate;
    }

    public long getValidityPeriodPolicy() {
        return validityPeriodPolicy;
    }

    public void setValidityPeriodPolicy(int validityPeriodPolicy) {
        this.validityPeriodPolicy = validityPeriodPolicy;
    }
}
