package com.example.tubes_01_l;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MenuDetailsFragment extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentListener listener;

    private TextView tv;

    public MenuDetailsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.menu_details_fragment,container, false);

        this.tv = view.findViewById(R.id.details_title);
        Bundle b = getArguments();
            if(b!=null){
                String title = b.getString("title");
                this.tv.setText(title);
            }else{
                Log.d("debug", "onCreateView: Menu Not Found");
            }
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

    public static MenuDetailsFragment newInstance(){
        MenuDetailsFragment fragment = new MenuDetailsFragment();
        return fragment;
    }

}
