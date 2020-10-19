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

    private TextView tvTitle, tvDeskripsi, tvTag, tvBahan, tvLangkah, tvResto;

    public MenuDetailsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.menu_details_fragment,container, false);

        this.tvTitle = view.findViewById(R.id.details_title);
        this.tvDeskripsi = view.findViewById(R.id.details_deskripsi);

        Bundle b = getArguments();
            if(b!=null){
                Menu menu = b.getParcelable("menu");
                Log.d("debug", "onCreateView: "+menu.title);
                this.tvTitle.setText(menu.title);
                //this.tvDeskripsi.setText(b.getString("deskripsi"));

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
