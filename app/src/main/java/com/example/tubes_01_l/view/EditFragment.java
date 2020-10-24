package com.example.tubes_01_l.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tubes_01_l.R;
import com.example.tubes_01_l.Sqlite;
import com.example.tubes_01_l.model.Menu;


public class EditFragment extends Fragment implements View.OnClickListener {

    private FragmentManager fragmentManager;

    private EditText edTitle, edDesc, edTag, edBahan, edLangkah, edResto;


    private FragmentListener listener;
    private Button btnEdit,btnHapus;
    private Sqlite sqlite;
    private String newTitle, newDesc;
    private String[] newTag, newBahan, newLangkah, newResto;
    private Menu menu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        this.sqlite =new Sqlite(this.getActivity());
        this.edTitle = view.findViewById(R.id.ed_new_title);
        this.edDesc = view.findViewById(R.id.ed_new_desc);
        this.edTag = view.findViewById(R.id.ed_new_tag);
        this.edBahan = view.findViewById(R.id.ed_new_bahan);
        this.edLangkah = view.findViewById(R.id.ed_new_langkah);
        this.edResto = view.findViewById(R.id.ed_new_resto);
        this.btnHapus=view.findViewById(R.id.btn_hapus);
        this.btnEdit=view.findViewById(R.id.btn_edit);

        this.btnHapus.setOnClickListener(this);

        Bundle b = getArguments();
        if (b != null) {
            Menu idMenu = b.getParcelable("editMenu");
            Log.d("edit", "onCreateView: " + idMenu.getId());

            this.menu = this.sqlite.getContact(idMenu.getId());
            String title = this.menu.getTitle();
            Log.d("edit", "onCreateView: sqnya- " + title);

            this.edTitle.setText(this.menu.getTitle());
            Log.d("edit", "onCreateView: sqnya2- " + title);

//            this.edTitle.setText(editMenu.getTitle());
//            this.edDesc.setText(editMenu.getDeskripsi());
//            this.edTag.setText(editMenu.getTag());
//
//            String[] arrBahan = menu.getBahan().split("\n");
//            String bahan = "";
//            for (int i = 0; i < arrBahan.length; i++) {
//                if (i == arrBahan.length - 1) {
//                    bahan += arrBahan[i];
//                } else {
//                    bahan += arrBahan[i] + ", ";
//                }
//            }
//            this.edBahan.setText(bahan);
//
//            String[] arrLangkah = menu.getLangkahMasak().split("\n");
//            String langkah = "";
//            for (int i = 0; i < arrLangkah.length; i++) {
//                if (i == arrLangkah.length - 1) {
//                    langkah += arrLangkah[i];
//                } else {
//                    langkah += arrLangkah[i] + ", ";
//                }
//            }
//            this.edLangkah.setText(langkah);
//
//            String[] arrResto = menu.getResto().split("\n");
//            String resto = "";
//            for (int i = 0; i < arrResto.length; i++) {
//                if (i == arrResto.length - 1) {
//                    resto += arrResto[i];
//                } else {
//                    resto += arrResto[i] + ", ";
//                }
//            }
//            this.edResto.setText(resto);
        } else {
            Log.d("debug", "onCreateView: Menu Not Found");
        }

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
            Log.d("edit", "hapus: "+this.menu.getId());
            sqlite.deleteRecord(this.menu.getId());
            listener.changePage(2);
        }
    }
}
