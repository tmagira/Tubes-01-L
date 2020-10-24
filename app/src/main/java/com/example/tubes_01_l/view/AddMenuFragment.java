package com.example.tubes_01_l.view;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tubes_01_l.R;
import com.example.tubes_01_l.Sqlite;
import com.example.tubes_01_l.model.Menu;

public class AddMenuFragment extends Fragment implements View.OnClickListener{
    private FragmentManager fragmentManager;
    private FragmentListener listener;
    private TextView edTitle, edDesc, edTag, edBahan, edLangkah, edResto;
    private Button btnAdd;
    private Sqlite sqlite;
    private String newTitle, newDesc;
    private String[] newTag, newBahan, newLangkah, newResto;

    public AddMenuFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_menu_add,container, false);

        this.sqlite =new Sqlite(this.getActivity());
        this.edTitle = view.findViewById(R.id.ed_new_title);
        this.edDesc = view.findViewById(R.id.ed_new_bahan);
        this.edTag = view.findViewById(R.id.ed_new_tag);
        this.edBahan = view.findViewById(R.id.ed_new_tag);
        this.edLangkah = view.findViewById(R.id.ed_new_langkah);
        this.edResto = view.findViewById(R.id.ed_new_resto);
        this.btnAdd = view.findViewById(R.id.btn_tambah_makanan);

        this.btnAdd.setOnClickListener(this);
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

    public static AddMenuFragment newInstance(){
        AddMenuFragment fragment = new AddMenuFragment();
        return fragment;
    }

    @Override
    public void onClick(View v) {
        if(v==this.btnAdd){
            this.newTitle = this.edTitle.getText().toString();
            this.newDesc = this.edDesc.getText().toString();
            this.newTag = this.edTag.getText().toString().split(",");
            this.newBahan = this.edBahan.getText().toString().split(",");
            this.newLangkah = this.edLangkah.getText().toString().split(",");
            this.newResto = this.edResto.getText().toString().split(",");
            Menu item = new Menu(0,this.newTitle, this.newDesc, this.newTag, this.newBahan, this.newLangkah, this.newResto);
            this.sqlite.addRecord(item);
            listener.changePage(2);
            edTitle.clearComposingText();
            edDesc.clearComposingText();
            edTag.clearComposingText();
            edBahan.clearComposingText();
            edLangkah.clearComposingText();
            edResto.clearComposingText();
        }
        else {
            this.sqlite.getContact(0);
            this.newTitle = this.edTitle.getText().toString();
            this.newDesc = this.edDesc.getText().toString();
            this.newTag = this.edTag.getText().toString().split(",");
            this.newBahan = this.edBahan.getText().toString().split(",");
            this.newLangkah = this.edLangkah.getText().toString().split(",");
            this.newResto = this.edResto.getText().toString().split(",");
            Menu item = new Menu(0,this.newTitle, this.newDesc, this.newTag, this.newBahan, this.newLangkah, this.newResto);
            this.sqlite.updateContact(item);
            listener.changePage(2);
            edTitle.clearComposingText();
            edDesc.clearComposingText();
            edTag.clearComposingText();
            edBahan.clearComposingText();
            edLangkah.clearComposingText();
            edResto.clearComposingText();
        }
    }
}
