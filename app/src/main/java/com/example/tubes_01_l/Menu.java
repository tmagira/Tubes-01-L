package com.example.tubes_01_l;

import android.os.Parcel;
import android.os.Parcelable;

public class Menu implements Parcelable {
    int id=0;
    String title;
    String deskripsi;
    String[] tag;
    String[] bahan;
    String[] langkahMasak;
    String[] resto;

    public Menu(int id,String title, String deskripsi, String[] tag,
                String[] bahan, String[] langkahMasak, String[] resto) {
        this.id =id;
        this.title = title;
        this.deskripsi = deskripsi;
        this.tag = tag;
        this.bahan = bahan;
        this.langkahMasak  = langkahMasak;
        this.resto = resto;
    }

    public Menu(Parcel in) {
        title = in.readString();
        deskripsi = in.readString();
        tag = in.createStringArray();
        bahan = in.createStringArray();
        langkahMasak = in.createStringArray();
        resto = in.createStringArray();
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };
    public void setTitle(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setTag(String[] tag) {
        this.tag = tag;
    }

    public void setBahan(String[] bahan) {
        this.bahan = bahan;
    }

    public void setLangkahMasak(String[] langkahMasak) {
        this.langkahMasak = langkahMasak;
    }

    public void setResto(String[] resto) {
        this.resto = resto;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String[] getTag() {
        return tag;
    }

    public String[] getBahan() {
        return bahan;
    }

    public String[] getLangkahMasak() {
        return langkahMasak;
    }

    public String[] getResto() {
        return resto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.deskripsi);
        dest.writeStringArray(this.tag);
        dest.writeStringArray(this.bahan);
        dest.writeStringArray(this.langkahMasak);
        dest.writeStringArray(this.resto);
    }
}
