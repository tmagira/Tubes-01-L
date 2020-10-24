package com.example.tubes_01_l.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tubes_01_l.R;
import com.example.tubes_01_l.Sqlite;
import com.example.tubes_01_l.model.Menu;
import com.example.tubes_01_l.presenter.MenuPresenter;

import java.util.List;

public class CariFragment extends Fragment implements MenuPresenter.IMainActivity, View.OnClickListener{

    private ListView listMenus;
    private MenuPresenter presenter;
    private MenuListAdapter adapter;
    private FragmentManager fragmentManager;
    private FragmentListener listener;
    private Sqlite sqlite;
    private Button btnCari;
    private EditText edCari;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_cari,container, false);

        this.btnCari = view.findViewById(R.id.btn_cari_menu);
        this.edCari = view.findViewById(R.id.ed_cari);

        this.sqlite = new Sqlite(this.getActivity());
        this.listMenus = view.findViewById(R.id.list_menu_cari);
        this.presenter = new MenuPresenter((MenuPresenter.IMainActivity) this,sqlite);
        this.adapter = new MenuListAdapter(requireActivity());

       this.btnCari.setOnClickListener(this);

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

    public static CariFragment newInstance(){
        CariFragment fragment = new CariFragment();
        return fragment;
    }

    @Override
    public void updateList(List<Menu> foods) {
        this.adapter.updateArray(foods);
    }

    @Override
    public void onClick(View v) {
        if(v==this.btnCari){
            String input = this.edCari.getText().toString().toLowerCase();

            String filter = "";
//            for(int i=0;i<input.length;i++){
//                if(input.length==1){
//                    filter+="'"+input[i]+"'";
//                }else{
//                    if(i!=input.length-1){
//                        filter+="'"+input[i]+"',";
//                    }else{
//                        filter+="'"+input[i]+"'";
//                    }
//                }
//            }
            this.presenter.loadSearchResult(input);
            this.listMenus.setAdapter(this.adapter);
        }
    }
}
