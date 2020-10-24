package com.example.tubes_01_l.presenter;


import com.example.tubes_01_l.Sqlite;
import com.example.tubes_01_l.model.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuPresenter {

   public interface IMainActivity{
        void updateList(List<Menu>foods);
    }
    protected Sqlite sqlite;
    protected List<Menu> menus;
    protected IMainActivity ui;

    public MenuPresenter(IMainActivity ui,Sqlite sqlite){
        this.menus = new ArrayList<>();
        this.ui = ui;
        this.sqlite=sqlite;
    }

    public void loadData(){
       this.menus=sqlite.getAllRecord();
        this.ui.updateList(this.menus);
    }

    public void loadSearchResult(String tag){
        this.menus=sqlite.getFilteredRecord(tag);
        this.ui.updateList(this.menus);
    }

    public int countItem(){
        return menus.size();
    }

    public void delete(int position){
        this.menus.remove(position);
        this.ui.updateList(this.menus);
    }
    public void addList(String title, String deskripsi, String[] tag,
                        String[] bahan, String[] langkahMasak, String[] resto){
        this.menus.add(new Menu(+1,title,deskripsi, tag, bahan, langkahMasak, resto));
        this.ui.updateList(this.menus);
        //this.ui.resetAddForm();
    }

}
