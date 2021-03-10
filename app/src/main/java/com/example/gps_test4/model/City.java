package com.example.gps_test4.model;

import java.util.ArrayList;

// 시·도를 담을 Class
public class City extends LocationData {
    private ArrayList<Gu> guList;

    public ArrayList<Gu> getGuList(){
        return getGuList();
    }

    // 구를 리스트에 넣어줄 세터 메서드
    public void addGu(Gu gu){
        //guList.add(gu);
        this.guList.add(gu);
    }

    public void setGuList(ArrayList<Gu> g){
        this.guList = g;
    }
}
