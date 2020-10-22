package com.example.tubes_01_l;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Random;

public class MenuRandom extends Fragment {
    TextView tvRes;
    String rn;
    MockMenu mm;
    private FragmentManager fragmentManager;
    private FragmentListener listener;

    public TextView tvTitle, tvDeskripsi, tvTag, tvBahan, tvLangkah, tvResto;
    public Button bt;


    public MenuRandom() {
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Random r = new Random();
        int randomNumber = r.nextInt(MockMenu.foodObjectArr.length);

        this.tvTitle = view.findViewById(R.id.details_title);
        this.tvDeskripsi = view.findViewById(R.id.details_deskripsi);
        this.tvTag = view.findViewById(R.id.details_tag);
        this.tvBahan = view.findViewById(R.id.details_bahan);
        this.tvLangkah = view.findViewById(R.id.details_langkah_masak);
        this.tvResto = view.findViewById(R.id.details_resto);
        this.bt= view.findViewById(R.id.btn_tambah_makanan);

        Bundle b = getArguments();
        if(b!=null){
            Menu menu = b.getParcelable("menu");
            this.tvTitle.setText(menu.title);
            this.tvDeskripsi.setText(menu.deskripsi);

            String tags = "";
            for(int i=0;i<menu.tag.length;i++){
                if (randomNumber==i){
                    this.tvTag.setText(tags);
                }
            }

            String bahan = "";
            for(int i=0;i<menu.bahan.length;i++){
               if (randomNumber==i){
                   this.tvBahan.setText(bahan);
               }
            }

            String langkah = "";
            for(int i=0;i<menu.langkahMasak.length;i++){
                if (randomNumber==i){
                    this.tvLangkah.setText(langkah);
                }
            }

            String resto="";
            for(int i=0;i<menu.resto.length;i++){
                if (randomNumber==i){
                    this.tvResto.setText(resto);
                }
            }

        }else{
            Log.d("debug", "onCreateView: Menu Not Found");
        }
        return view;
    }

    public static MenuRandom newInstance() {
        MenuRandom fragment = new MenuRandom();
        return fragment;
    }

}
