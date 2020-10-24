package com.example.tubes_01_l.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tubes_01_l.R;
import com.example.tubes_01_l.model.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuListAdapter extends BaseAdapter {

    private List<Menu> listMenu;
    private Activity activity;

    public MenuListAdapter(Activity activity) {
        this.listMenu = new ArrayList<Menu>();
        this.activity = activity;
    }

    private class ViewHolder{
        protected TextView title;
        public ViewHolder(View view) {
            this.title = view.findViewById(R.id.tv_menu_title);
        }
    }

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
        return this.listMenu.size();
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
        ViewHolder viewHolder = new ViewHolder(convertView);
        viewHolder.title.setText(currentMenu.getTitle());
        return convertView;
    }
}
