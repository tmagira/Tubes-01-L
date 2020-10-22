package com.example.tubes_01_l;


import java.util.ArrayList;
import java.util.List;

public class MenuPresenter {

    interface IMainActivity{
        void updateList(List<Menu>foods);
    }

    protected List<Menu> menus;
    protected IMainActivity ui;

    public MenuPresenter(IMainActivity ui){
        this.menus = new ArrayList<>();
        this.ui = ui;
    }

    public void loadData(){
        for (Menu menu:MockMenu.foodObjectArr) {
            this.menus.add(menu);
        }
        this.ui.updateList(this.menus);
    }
    public void delete(int position){
        this.menus.remove(position);
        this.ui.updateList(this.menus);
    }
    public void addList(String title, String deskripsi, String[] tag,
                        String[] bahan, String[] langkahMasak, String[] resto){
        this.menus.add(new Menu(title,deskripsi, tag, bahan, langkahMasak, resto));
        this.ui.updateList(this.menus);
        //this.ui.resetAddForm();
    }

}
