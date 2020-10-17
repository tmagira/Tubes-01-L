package com.example.tubes_01_l;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.List;

public class MenuFragment extends Fragment implements MenuPresenter.IMainActivity {

    private ListView listMenus;
    private MenuPresenter presenter;
    private MenuListAdapter adapter;
    private FragmentManager fragmentManager;
    private FragmentListener listener;

    public MenuFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.menu_fragment,container, false);
<<<<<<< HEAD
=======

        this.listMenus = view.findViewById(R.id.list_menu);
        this.presenter = new MenuPresenter(this);
        this.adapter = new MenuListAdapter(requireActivity());

        this.presenter.loadData();
        this.listMenus.setAdapter(this.adapter);

>>>>>>> ff234008204d22eacb5ccbe2b144023b89120cb7
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

    public static MenuFragment newInstance(){
        MenuFragment fragment = new MenuFragment();
//        Bundle args = new Bundle();
//        args.putString("title", title);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void updateList(List<Menu> foods) {
        this.adapter.updateArray(foods);
    }
}
