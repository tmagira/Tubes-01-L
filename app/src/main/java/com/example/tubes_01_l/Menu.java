package com.example.tubes_01_l;

public class Menu {
    String title;
    String deskripsi;
    String[] tag;
    String[] bahan;
    String[] langkahMasak;
    String[] resto;

    public Menu(String deskripsi, String[] tag, String[] bahan, String[] langkahMasak, String[] resto) {
        this.deskripsi = deskripsi;
        this.tag = tag;
        this.bahan = bahan;
        this.langkahMasak = langkahMasak;
        this.resto = resto;
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
}
