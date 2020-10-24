package com.example.tubes_01_l.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tubes_01_l.R;
import com.example.tubes_01_l.Sqlite;
import com.example.tubes_01_l.model.Menu;
import com.example.tubes_01_l.presenter.MenuPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MenuFragment extends Fragment implements MenuPresenter.IMainActivity, View.OnClickListener {

    private ListView listMenus;
    private MenuPresenter presenter;
    private MenuListAdapter adapter;
    private FragmentManager fragmentManager;
    private FragmentListener listener;
    private FloatingActionButton fabAdd;
    private Sqlite sqlite;
    public MenuFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.menu_fragment,container, false);
        this.sqlite = new Sqlite(this.getActivity());
        this.listMenus = view.findViewById(R.id.list_menu);
        this.presenter = new MenuPresenter((MenuPresenter.IMainActivity) this,sqlite);
        this.adapter = new MenuListAdapter(requireActivity());
        this.fabAdd = view.findViewById(R.id.fab_add);

        this.presenter.loadData();
        this.listMenus.setAdapter(this.adapter);

        this.fabAdd.setOnClickListener(this);

        this.listMenus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity mnl = (MainActivity)getActivity();
                Menu currentMenu = (Menu)adapter.getItem(position);
                mnl.passMenu(currentMenu);
            }
        });

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
        return fragment;
    }


    @Override
    public void onClick(View v) {
        if(v==this.fabAdd){
            listener.changePage(4);
        }
    }

    @Override
    public void updateList(List<Menu> foods) {
        this.adapter.updateArray(foods);
    }
}
