package com.example.tubes_01_l;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MenuDetailsFragment extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentListener listener;

    public TextView tvTitle, tvDeskripsi, tvTag, tvBahan, tvLangkah, tvResto;

    public MenuDetailsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.menu_details_fragment,container, false);

        this.tvTitle = view.findViewById(R.id.details_title);
        this.tvDeskripsi = view.findViewById(R.id.details_deskripsi);
        this.tvTag = view.findViewById(R.id.details_tag);
        this.tvBahan = view.findViewById(R.id.details_bahan);
        this.tvLangkah = view.findViewById(R.id.details_langkah_masak);
        this.tvResto = view.findViewById(R.id.details_resto);

        Bundle b = getArguments();
            if(b!=null){
                Menu menu = b.getParcelable("menu");
                this.tvTitle.setText(menu.title);
                this.tvDeskripsi.setText(menu.deskripsi);

                String tags = "";
                for(int i=0;i<menu.tag.length;i++){
                    if(i!=menu.tag.length-1){
                        tags+=menu.tag[i]+", ";
                    }else{
                        tags+=menu.tag[i];
                    }
                }
                this.tvTag.setText(tags);

                String bahan = "";
                for(int i=0;i<menu.bahan.length;i++){
                    bahan+= menu.bahan[i] + "\n";
                }
                this.tvBahan.setText(bahan);

                String langkah = "";
                for(int i=0;i<menu.langkahMasak.length;i++){
                    langkah+=i+1+". "+menu.langkahMasak[i]+"\n";
                }
                this.tvLangkah.setText(langkah);

                String resto="";
                for(int i=0;i<menu.resto.length;i++){
                    if(i!=menu.resto.length-1){
                        resto+=menu.resto[i]+", ";
                    }else{
                        resto+=menu.resto[i];
                    }
                }
                this.tvResto.setText(resto);

            }else{
                Log.d("debug", "onCreateView: Menu Not Found");
            }
        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentListener){
            this.listener = (FragmentListener) context;
        }else{
            throw new ClassCastException(context.toString()+ " Must Implement Fragment Listener");
        }
    }

    public static MenuDetailsFragment newInstance(){
        MenuDetailsFragment fragment = new MenuDetailsFragment();
        return fragment;
    }

}
