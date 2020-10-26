package com.example.tubes_01_l.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tubes_01_l.R;
import com.example.tubes_01_l.Sqlite;
import com.example.tubes_01_l.model.Menu;
import com.example.tubes_01_l.presenter.MenuPresenter;

import java.util.List;

public class CariFragment extends Fragment implements MenuPresenter.IMainActivity, View.OnClickListener{

    private ListView listMenus;
    private MenuPresenter presenter;
    private MenuListAdapter adapter;
    private FragmentManager fragmentManager;
    private FragmentListener listener;
    private Sqlite sqlite;
    private Button btnCari;
    private EditText edCari;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_cari,container, false);

        this.btnCari = view.findViewById(R.id.btn_cari_menu);
        this.edCari = view.findViewById(R.id.ed_cari);

        this.sqlite = new Sqlite(this.getActivity());
        this.listMenus = view.findViewById(R.id.list_menu_cari);
        this.presenter = new MenuPresenter((MenuPresenter.IMainActivity) this,sqlite);
        this.adapter = new MenuListAdapter(requireActivity()) ;

       this.btnCari.setOnClickListener(this);

        this.listMenus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("debug", "onItemClick: clicked "+position);
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

    public static CariFragment newInstance(){
        CariFragment fragment = new CariFragment();
        return fragment;
    }

    @Override
    public void updateList(List<Menu> foods) {
        this.adapter.updateArray(foods);
    }

    @Override
    public void onClick(View v) {
        if(v==this.btnCari){
            String input = this.edCari.getText().toString().toLowerCase();
            this.presenter.loadSearchResult(input);
            this.listMenus.setAdapter(this.adapter);
            this.hideKeyboard(getActivity());
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
