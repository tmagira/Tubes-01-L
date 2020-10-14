package com.example.tubes_01_l;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class MenuListAdapter extends BaseAdapter {

    private List<Menu> listMenu;
    private Activity activity;

    public MenuListAdapter(Activity activity) {
        this.listMenu = listMenu;
        this.activity = activity;
    }

//    public ViewHolder(View view){
//
//    }

    public void addLine(Menu newMenu){
        this.listMenu.add(newMenu);
        this.notifyDataSetChanged();
    }

    public void updateArray(List<Menu> menus){
        this.listMenu = menus;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listMenu.size();
    }

    @Override
    public Object getItem(int position) {
        return listMenu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        View convertView = LayoutInflater.from(this.activity).inflate(R.layout.item_list_menu, parent, false);
        Menu currentMenu = (Menu)this.getItem(i);

        //currentMenu.title

        //NOT DONE YET HEHE
        return null;
    }
}
