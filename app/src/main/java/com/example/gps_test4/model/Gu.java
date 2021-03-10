package com.example.gps_test4.model;

import java.util.ArrayList;

// 시·군·구를 담을 Class
public class Gu extends LocationData {
    private ArrayList<Dong> dongList;

    public ArrayList<Dong> getDongList(){
        return getDongList();
    }

    // 동을 동 리스트에 넣어주는 세터 메서드
    public void addDong(Dong dong){
        this.dongList.add(dong);
    }

    // 이 setDongList 세터 메서드는 동을 받을 경우 dongList에 쌓아야 하는데
    // 쌓는게 아니라 새로운 ArrayList 로 치환한다.
    // 때문에 addDong 세터 메서드를 생성하였다. -> 동을 dongList에 넣어준다.
    public void setDongList(ArrayList<Dong> dongList){
        this.dongList = dongList;
    }
}