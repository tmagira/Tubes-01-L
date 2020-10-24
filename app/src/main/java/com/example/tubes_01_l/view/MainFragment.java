package com.example.tubes_01_l.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tubes_01_l.Sqlite;
import com.example.tubes_01_l.presenter.MenuPresenter;
import com.example.tubes_01_l.R;
import com.example.tubes_01_l.model.Menu;

import java.util.List;

public class MainFragment extends Fragment implements MenuPresenter.IMainActivity, View.OnClickListener{
    private FragmentManager fragmentManager;
    private FragmentListener listener;
    private MenuPresenter presenter;
    private MenuListAdapter adapter;
    private Button btnCari;
    private Sqlite sqlite;
    public MainFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_main,container, false);

        this.sqlite = new Sqlite(this.getActivity());
        this.btnCari = view.findViewById(R.id.btn_cari);
        this.presenter = new MenuPresenter((MenuPresenter.IMainActivity) this,sqlite);
        this.adapter = new MenuListAdapter(requireActivity());

        this.presenter.loadData();

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

    public static MainFragment newInstance(){
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        if(v==this.btnCari){
            int length = this.presenter.countItem();
            int randomNumber = (int) ((Math.random() * length));
            MainActivity mnl = (MainActivity)getActivity();
            Menu currentMenu = (Menu)adapter.getItem(randomNumber);
            mnl.passMenu(currentMenu);
        }
    }

    public static MainFragment getInstance() {
        return instance;
    }

    @Override
    public void updateList(List<Menu> foods) {
        this.adapter.updateArray(foods);
    }
}
