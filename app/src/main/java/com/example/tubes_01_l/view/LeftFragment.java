package com.example.tubes_01_l.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tubes_01_l.Sqlite;
import com.example.tubes_01_l.databinding.FragmentLeftBinding;
import com.example.tubes_01_l.presenter.MenuPresenter;

public class LeftFragment extends Fragment implements View.OnClickListener {
    FragmentListener listener;
    FragmentLeftBinding binding;
    TextView tv1, tv2, tv3, tv4, tv5;
    MainActivity activity;
    private MenuPresenter presenter;
    private MenuListAdapter adapter;
    private Sqlite sqlite;
    public LeftFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLeftBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        binding.home.setOnClickListener(this);
        binding.cari.setOnClickListener(this);
        binding.menu.setOnClickListener(this);
        binding.setting.setOnClickListener(this);
        binding.exit.setOnClickListener(this);
        this.activity = ((MainActivity) getActivity());
        return view;
    }

    public static LeftFragment newInstance(String title) {
        LeftFragment fragment = new LeftFragment();
        return fragment;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListener) {
            this.listener = (FragmentListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement FragmentListener");
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.home.getId()) {
            this.activity.changePage(1);
        } else if (v.getId() == binding.menu.getId()) {
            this.activity.changePage(2);
        } else if (v.getId() == binding.exit.getId()) {
            this.activity.closeApplication();
        }else if(v.getId()==binding.cari.getId()){
            this.activity.changePage(6);
        }else if (v.getId() == binding.setting.getId()) {
            this.activity.changePage(8);
        }
    }

}