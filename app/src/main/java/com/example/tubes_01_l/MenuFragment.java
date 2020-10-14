package com.example.tubes_01_l;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MenuFragment extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentListener listener;

    public MenuFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.menu_fragment,container, false);

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

    public static MenuFragment newInstance(String title){
        MenuFragment fragment = new MenuFragment();
//        Bundle args = new Bundle();
//        args.putString("title", title);
//        fragment.setArguments(args);
        return fragment;
    }
}
