package com.example.tubes_01_l;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.tubes_01_l.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements FragmentListener {

    private MenuFragment menuFragment;
    private MainFragment mainFragment;
    FragmentManager fragmentManager;
    FragmentTransaction ft;
    ActivityMainBinding binding;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //test
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = this.binding.getRoot();
       // this.setSupportActionBar(this.binding.toolbar);
        setContentView(view);

        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, this.binding.drawerLayout, this.binding.toolbar, 0, 0);
        this.binding.drawerLayout.addDrawerListener(abdt);
        abdt.syncState();

        this.fragmentManager = this.getSupportFragmentManager();
        this.mainFragment = MainFragment.newInstance();
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.add(R.id.fragment_container, this.mainFragment).addToBackStack(null).commit();
    }

    @Override
    public void changePage(int page) {
        this.ft = this.fragmentManager.beginTransaction();

        if (page == 1) {
            Log.d("debug", "changePage: page1");
            if (this.mainFragment.isAdded()) {
                ft.show(this.mainFragment);
            } else {
                ft.add(R.id.fragment_container, this.mainFragment);
            }
            if (this.menuFragment.isAdded()) {
                ft.hide(this.menuFragment);
            }
        } else if (page == 2) {
            if (this.menuFragment.isAdded()) {
                ft.show(this.menuFragment);
            } else {
                ft.add(R.id.fragment_container, this.menuFragment).addToBackStack(null);
            }
            if (this.mainFragment.isAdded()) {
                ft.hide(this.mainFragment);
            }
            this.ft.commit();
            this.binding.drawerLayout.closeDrawers();
        }
    }

    @Override
    public void closeApplication() {
        this.moveTaskToBack(true);
        this.finish();
        this.binding.drawerLayout.closeDrawers();
    }
}