package com.example.tubes_01_l;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tubes_01_l.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements FragmentListener {

    private MenuFragment menuFragment;
    private MainFragment mainFragment;
    private MenuDetailsFragment menuDetailsFragment;
    private AddMenuFragment addMenuFragment;
    private MenuRandom randomMenu;


    FragmentManager fragmentManager;
    FragmentTransaction ft;
    ActivityMainBinding binding;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = this.binding.getRoot();
        setContentView(view);

       // this.setSupportActionBar(this.binding.toolbar);

        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, this.binding.drawerLayout, this.binding.toolbar, 0, 0);
        this.binding.drawerLayout.addDrawerListener(abdt);
        abdt.syncState();


        this.fragmentManager = this.getSupportFragmentManager();
        this.mainFragment = MainFragment.newInstance();
        this.menuFragment = MenuFragment.newInstance();
        this.menuDetailsFragment = MenuDetailsFragment.newInstance();
        this.addMenuFragment = AddMenuFragment.newInstance();

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
                ft.add(R.id.fragment_container, this.mainFragment).addToBackStack(null);
            }
            if (this.menuFragment.isAdded()) {
                ft.hide(this.menuFragment);
            }
            if (this.menuDetailsFragment.isAdded()) {
                ft.hide(this.menuDetailsFragment);
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
            if(this.menuDetailsFragment.isAdded()){
                ft.hide(this.menuDetailsFragment);
            }
        }else if (page == 3) {
            if (this.menuDetailsFragment.isAdded()) {
                ft.show(this.menuDetailsFragment);
            } else {
                ft.add(R.id.fragment_container, this.menuDetailsFragment).addToBackStack(null);
            }
            if (this.mainFragment.isAdded()) {
                ft.hide(this.mainFragment);
            }
            if(this.menuFragment.isAdded()){
                ft.hide((this.menuFragment));
            }
        }else if (page == 4) {
            if (this.addMenuFragment.isAdded()) {
                ft.show(this.addMenuFragment);
            } else {
                ft.add(R.id.fragment_container, this.addMenuFragment).addToBackStack(null);
            }
            if (this.mainFragment.isAdded()) {
                ft.hide(this.mainFragment);
            }
            if(this.menuFragment.isAdded()){
                ft.hide((this.menuFragment));
            }
        }
        else if (page == 5) {
            if (this.randomMenu.isAdded()) {
                ft.show(this.randomMenu);
            } else {
                ft.add(R.id.fragment_container, this.randomMenu).addToBackStack(null);
            }
            if (this.mainFragment.isAdded()) {
                ft.hide(this.mainFragment);
            }
            if(this.menuFragment.isAdded()){
                ft.hide((this.menuFragment));
            }
        }
        this.ft.commit();
        this.binding.drawerLayout.closeDrawers();
    }

    @Override
    public void closeApplication() {
        this.moveTaskToBack(true);
        this.finish();
        this.binding.drawerLayout.closeDrawers();
    }

    public void passMenu(Menu menu) {
        this.ft = this.fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putParcelable("menu", menu);
        this.menuDetailsFragment.setArguments(bundle);
        changePage(3);
    }
}