package com.example.tubes_01_l.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.tubes_01_l.R;
import com.example.tubes_01_l.model.Menu;

public class EditFragment extends Fragment {

    private EditText edTitle, edDesc, edTag, edBahan, edLangkah, edResto;

    private FragmentListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_edit,container, false);

        this.edTitle = view.findViewById(R.id.ed_new_title);
        this.edDesc = view.findViewById(R.id.ed_new_desc);
        this.edTag = view.findViewById(R.id.ed_new_tag);
        this.edBahan = view.findViewById(R.id.ed_new_bahan);
        this.edLangkah = view.findViewById(R.id.ed_new_langkah);
        this.edResto = view.findViewById(R.id.ed_new_resto);

        Bundle b = getArguments();
        if (b != null) {
            Menu menu = b.getParcelable("editMenu");
            Log.d("edit", "onCreateView: "+menu.getId());

            this.edTitle.setText(menu.getTitle());
            this.edDesc.setText(menu.getDeskripsi());
            this.edTag.setText(menu.getTag());

            String[] arrBahan = menu.getBahan().split("\n");
            String bahan = "";
            for(int i=0;i<arrBahan.length;i++){
               if(i==arrBahan.length-1){
                   bahan+=arrBahan[i];
               }else {
                   bahan+=arrBahan[i]+", ";
               }
            }
            this.edBahan.setText(bahan);

            String[] arrLangkah = menu.getLangkahMasak().split("\n");
            String langkah = "";
            for(int i=0;i<arrLangkah.length;i++){
                if(i==arrLangkah.length-1){
                    langkah+=arrLangkah[i];
                }else {
                    langkah+=arrLangkah[i]+", ";
                }
            }
            this.edLangkah.setText(langkah);

            String[] arrResto = menu.getResto().split("\n");
            String resto = "";
            for(int i=0;i<arrResto.length;i++){
                if(i==arrResto.length-1){
                    resto+=arrResto[i];
                }else {
                    resto+=arrResto[i]+", ";
                }
            }
            this.edResto.setText(resto);
        } else {
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

    public static EditFragment newInstance(){
        EditFragment fragment = new EditFragment();
        return fragment;
    }
}
