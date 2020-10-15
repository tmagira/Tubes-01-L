package com.example.tubes_01_l;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements FragmentListener{

    private MenuFragment menuFragment;
    private MainFragment mainFragment;

    FragmentManager fragmentManager;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.menuFragment = MenuFragment.newInstance();
        this.mainFragment = MainFragment.newInstance();
    }

    @Override
    public void changePage(int page) {
        this.ft = this.fragmentManager.beginTransaction();

        if(page==1){
            Log.d("debug", "changePage: page1");
            if(this.mainFragment.isAdded()){
                ft.show(this.mainFragment);
            }else{
                ft.add(R.id.fragment_container,this.mainFragment);
            }
            if(this.menuFragment.isAdded()){
                ft.hide(this.menuFragment);
            }
        }else if(page==2){
            if(this.menuFragment.isAdded()){
                ft.show(this.menuFragment);
            }else{
                ft.add(R.id.fragment_container,this.menuFragment).addToBackStack(null);
            }
            if(this.mainFragment.isAdded()){
                ft.hide(this.mainFragment);
            }
        }
        this.ft.commit();

    }

    @Override
    public void closeApplication() {
        this.moveTaskToBack(true);
        this.finish();
    }
}