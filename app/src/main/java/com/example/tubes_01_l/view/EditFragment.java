package com.example.tubes_01_l.view;

import android.content.Context;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditFragment extends Fragment implements View.OnClickListener {

    private FragmentManager fragmentManager;
    private FragmentListener listener;
    private TextView edTitle, edDesc, edTag, edBahan, edLangkah, edResto;
    private Button btnEdit,btnHapus;
    private Sqlite sqlite;
    private String newTitle, newDesc;
    private String[] newTag, newBahan, newLangkah, newResto;
    private Menu menu;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        this.sqlite = new Sqlite(this.getActivity());
        this.edTitle = view.findViewById(R.id.ed_new_title);
        this.edDesc = view.findViewById(R.id.ed_new_desc);
        this.edTag = view.findViewById(R.id.ed_new_tag);
        this.edBahan = view.findViewById(R.id.ed_new_bahan);
        this.edLangkah = view.findViewById(R.id.ed_new_langkah);
        this.edResto = view.findViewById(R.id.ed_new_resto);
        this.btnEdit = view.findViewById(R.id.btn_edit);
        this.btnHapus=view.findViewById(R.id.btn_hapus);

        this.btnEdit.setOnClickListener(this);
        this.btnHapus.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListener) {
            this.listener = (FragmentListener) context;
        } else {
            throw new ClassCastException(context.toString() + " Must Implement Fragment Listener");
        }
    }

    public static EditFragment newInstance() {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        if (v == this.btnEdit) {
            Menu item = new Menu(0, this.newTitle, this.newDesc, this.newTag, this.newBahan, this.newLangkah, this.newResto);
            this.sqlite.getFilteredRecord(this.newTitle);
            this.newTitle = this.edTitle.getText().toString().toLowerCase();
            this.newDesc = this.edDesc.getText().toString();
            this.newTag = this.edTag.getText().toString().replaceAll(" ", "").split(",");
            this.newBahan = this.edBahan.getText().toString().replaceAll(" ", "").split(",");
            this.newLangkah = this.edLangkah.getText().toString().replaceAll(" ", "").split(",");
            this.newResto = this.edResto.getText().toString().replaceAll(" ", "").split(",");
            this.sqlite.updateContact(item);
            listener.changePage(2);
            edTitle.setText(null);
            edDesc.setText(null);
            edTag.setText(null);
            edBahan.setText(null);
            edLangkah.setText(null);
            edResto.setText(null);
        }
        else if(v==this.btnHapus){

        }
    }
}
