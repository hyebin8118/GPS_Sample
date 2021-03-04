package com.example.gps_test4.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gps_test4.R;
import com.example.gps_test4.model._Location;

import java.util.ArrayList;

public class LocationArrayAdapter extends BaseAdapter {
    Context context;
    private ArrayList<_Location> items;

    /*
    * 생성자
    * TODO 숙제1 : 생성자가 무엇인지 공부하여 여기에 주석으로 직접 기재하기.(복붙하지 않기)
    * */

    public LocationArrayAdapter(Context context, ArrayList<_Location> locations){
        this.context = context;
        this.items = locations;
    }

    // arrayList 자료형의 items 변수로 부터
    @Override
    public int getCount(){  // 카운트(개수)를 가져오다. -- 넘버란? 숫자. -- getCount 와 유사하게 사용하는 함수 이름 : getSize, getLength
        // 참이면 0을 반환, 거짓이면 items의 size를 반환
        return (items == null)? 0 : items.size();
    }

    // position 에 해당하는 item 반환
    // arrayList 자료형의 items 변수로부터 position(==index) 번 째에 위치한 아이템(_Location 객체)을 가져와 주세요.
    @Override
    public Object getItem(int position){
        return items.get(position);
    }

    // @ : At / @ + 말 : 첨언
    // 옛날에 존재한 함수 (getPosition 과 같은 작동을 한다.) = 때문에 굳이 사용하지 않아도 된다.
    @Override
    public long getItemId(int position){
        return 0;
    }

    // View(Button, EditText, TextView, Activity…) 를 가져와 주세요.(xml에서 쓰이는 모든 Component)
    //
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.location_spinner_item, parent, false);
        }
        _Location location = items.get(position);
        TextView spinner_text = convertView.findViewById(R.id.spinner_text);
        spinner_text.setText(location.getLocation());
        return convertView;
    }
}