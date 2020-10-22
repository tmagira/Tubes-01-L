package com.example.tubes_01_l;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.List;

public class MainFragment extends Fragment implements MenuPresenter.IMainActivity, View.OnClickListener{
    private FragmentManager fragmentManager;
    private FragmentListener listener;
    private Button btnCari;

    private MenuPresenter presenter;
    private MenuListAdapter adapter;

    public MainFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_main,container, false);

        this.btnCari = view.findViewById(R.id.btn_cari);

        this.btnCari.setOnClickListener(this);

        this.presenter = new MenuPresenter((MenuPresenter.IMainActivity) this);
        this.adapter = new MenuListAdapter(requireActivity());

        this.presenter.loadData();




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
            int randomNumber = (int) ((Math.random() * (length)));
            MainActivity mnl = (MainActivity)getActivity();
            Menu currentMenu = (Menu)adapter.getItem(randomNumber);
            mnl.passMenu(currentMenu);
            Log.d("out", "onClick: "+randomNumber);
        }
    }

    @Override
    public void updateList(List<Menu> foods) {
        this.adapter.updateArray(foods);
    }
}
