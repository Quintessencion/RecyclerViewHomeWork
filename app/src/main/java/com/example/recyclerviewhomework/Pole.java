package com.example.recyclerviewhomework;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pole implements Parcelable {
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

    private Pole(Parcel in) {
        resIdImagePolicyType = in.readInt();
        resIdImageNext = in.readInt();
        resIdImageShoppingCart = in.readInt();
        policyType = in.readString();
        policyNumber = in.readString();
        objectOfInsurance = in.readString();
        validityPeriodPolicy = in.readLong();
    }

    public static final Creator<Pole> CREATOR = new Creator<Pole>() {
        @Override
        public Pole createFromParcel(Parcel in) {
            return new Pole(in);
        }

        @Override
        public Pole[] newArray(int size) {
            return new Pole[size];
        }
    };

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

    public void setValidityPeriodPolicy(long validityPeriodPolicy) {
        this.validityPeriodPolicy = validityPeriodPolicy;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(resIdImagePolicyType);
//        dest.writeInt(resIdImageNext);
//        dest.writeInt(resIdImageShoppingCart);
//        dest.writeString(policyType);
//        dest.writeString(policyNumber);
//        dest.writeString(objectOfInsurance);
//        dest.writeLong(validityPeriodPolicy);
    }
}
