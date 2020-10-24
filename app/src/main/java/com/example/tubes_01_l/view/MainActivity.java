package com.example.tubes_01_l.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tubes_01_l.R;
import com.example.tubes_01_l.databinding.ActivityMainBinding;
import com.example.tubes_01_l.model.Menu;


public class MainActivity extends AppCompatActivity implements FragmentListener {

    private MenuFragment menuFragment;
    private MainFragment mainFragment;
    private MenuDetailsFragment menuDetailsFragment;
    private AddMenuFragment addMenuFragment;


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

       this.setSupportActionBar(this.binding.toolbar);
        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, this.binding.drawerLayout, this.binding.toolbar, 0, 0);
        this.binding.drawerLayout.addDrawerListener(abdt);
        abdt.syncState();


        this.fragmentManager = this.getSupportFragmentManager();
        this.mainFragment = MainFragment.newInstance();
        this.menuFragment = MenuFragment.newInstance();
        this.menuDetailsFragment = MenuDetailsFragment.newInstance();
        this.addMenuFragment = AddMenuFragment.newInstance();

        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment fr = fragmentManager.findFragmentById(R.id.menu_list);
                if(fr!=null){
                    Log.e("fragment=", fr.getClass().getSimpleName());
                }
            }
        });
        ft.add(R.id.fragment_container, this.mainFragment).addToBackStack(null).commit();
       // changePage(1);
    }

    @Override
    public void changePage(int page) {
        this.ft = this.fragmentManager.beginTransaction();

        if(page == 1){
            ft.replace(R.id.fragment_container, this.mainFragment).addToBackStack(null);
        } else if(page == 2){
            ft.replace(R.id.fragment_container, this.menuFragment).addToBackStack(null);
        }else if(page == 3){
            ft.replace(R.id.fragment_container, this.menuDetailsFragment).addToBackStack(null);
        }else if(page == 4){
            ft.replace(R.id.fragment_container, this.addMenuFragment).addToBackStack(null);
        }else if(page == 5){
            ft.replace(R.id.fragment_container, this.addMenuFragment).addToBackStack(null);
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