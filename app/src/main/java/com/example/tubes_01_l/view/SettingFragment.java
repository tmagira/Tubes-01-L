package com.example.tubes_01_l.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tubes_01_l.R;

public class SettingFragment extends Fragment implements View.OnClickListener {
    FragmentListener listener;
    private TextView tvJudul,tvubah;
    private Switch sw;

    public SettingFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.setting_fragment, container, false);
        this.tvJudul = view.findViewById(R.id.judul_setting);
        this.tvubah =view.findViewById(R.id.tulisanUbah);
        this.sw = view.findViewById(R.id.switchTema);
        return view;
    }
    public void onClick(View v){
        if (v==this.sw){
        listener.changePage(8);
        }
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
    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }
}
