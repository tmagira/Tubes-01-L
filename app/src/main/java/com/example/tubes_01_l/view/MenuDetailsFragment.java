package com.example.tubes_01_l.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tubes_01_l.R;
import com.example.tubes_01_l.Sqlite;
import com.example.tubes_01_l.model.Menu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MenuDetailsFragment extends Fragment implements View.OnClickListener{
    private FragmentManager fragmentManager;
    private FragmentListener listener;

    public TextView tvTitle, tvDeskripsi, tvTag, tvBahan, tvLangkah, tvResto;
    public FloatingActionButton fabEdit;
    private Sqlite sqlite;
    private Bundle b;

    public MenuDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_details_fragment, container, false);

        this.tvTitle = view.findViewById(R.id.details_title);
        this.tvDeskripsi = view.findViewById(R.id.details_deskripsi);
        this.tvTag = view.findViewById(R.id.details_tag);
        this.tvBahan = view.findViewById(R.id.details_bahan);
        this.tvLangkah = view.findViewById(R.id.details_langkah_masak);
        this.tvResto = view.findViewById(R.id.details_resto);
        this.fabEdit = view.findViewById(R.id.fab_edit);
        this.fabEdit.setOnClickListener(this);
        this.sqlite = new Sqlite(this.getActivity());

        this.b = getArguments();
        if (b != null) {
            Menu menu = b.getParcelable("menu");
            this.tvTitle.setText(menu.getTitle());
            this.tvDeskripsi.setText(menu.getDeskripsi());

            String tags = "";
            for (int i = 0; i < menu.getTag().length(); i++) {
                if (i != menu.getTag().length() - 1) {
                    tags = menu.getTag() ;
                } else {
                    tags = menu.getTag();
                }
            }
            this.tvTag.setText(tags);

            String bahan = "";
            for (int i = 0; i < menu.getBahan().length(); i++) {
                bahan = menu.getBahan() + "\n";
            }
            this.tvBahan.setText(bahan);

            String langkah = "";
            for(int i=0;i<menu.getLangkahMasak().length();i++){
                langkah =menu.getLangkahMasak()+"\n";
            }
            this.tvLangkah.setText(langkah);

            String resto = "";
            for (int i = 0; i < menu.getResto().length(); i++) {
                if (i != menu.getResto().length() - 1) {
                    resto = menu.getResto() + ", ";
                } else {
                    resto = menu.getResto();
                }
            }
            this.tvResto.setText(resto);

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

    public static MenuDetailsFragment newInstance() {
        MenuDetailsFragment fragment = new MenuDetailsFragment();
        return fragment;
    }

    public void onClick(View v) {
        if (v.getId() == this.fabEdit.getId()) {
            MainActivity mnl = (MainActivity)getActivity();
            Menu thisMenu = b.getParcelable("menu");
            mnl.editMenu(thisMenu);
        }
    }
}
