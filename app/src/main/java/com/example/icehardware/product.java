package com.example.icehardware;

import android.os.Parcel;
import android.os.Parcelable;

public class product implements Parcelable{
    private String id;
    private String brand;
    private String nama;
    private String jenis;
    private String harga;

    public product() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getJenis() {
        return jenis;
    }
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    public String getHarga() {
        return harga;
    }
    public void setHarga(String harga) {
        this.harga = harga;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.brand);
        dest.writeString(this.nama);
        dest.writeString(this.jenis);
        dest.writeString(this.harga);
    }

    protected product(Parcel in) {
        this.id = in.readString();
        this.brand = in.readString();
        this.nama = in.readString();
        this.jenis = in.readString();
        this.harga = in.readString();
    }

    public static final Parcelable.Creator<product> CREATOR = new Parcelable.Creator<product>() {
        @Override
        public product createFromParcel(Parcel source) {
            return new product(source);
        }
        @Override
        public product[] newArray(int size) {
            return new product[size];
        }
    };
}

