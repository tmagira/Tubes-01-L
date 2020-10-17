package com.example.tubes_01_l;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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

        this.listMenus = view.findViewById(R.id.list_menu);
        this.presenter = new MenuPresenter(this);
        this.adapter = new MenuListAdapter(requireActivity());

        this.presenter.loadData();
        this.listMenus.setAdapter(this.adapter);

        this.listMenus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("debug", "onItemClick: clicked "+position);
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
    public void updateList(List<Menu> foods) {
        this.adapter.updateArray(foods);
    }


}
