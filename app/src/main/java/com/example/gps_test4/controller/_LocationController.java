package com.example.gps_test4.controller;

import android.content.Context;

import com.example.gps_test4.model._Location;

import java.util.ArrayList;
import java.util.HashMap;

public class _LocationController {
    final String FILE_NAME = "administrative_code.xls";
    final String COLUMN_CODE = "코드";
    final String COLUMN_LOCATION = "주소";
    final String COLUMN_ISEXIST = "여부";
    ExcelController excelController;
    public _LocationController(Context context){
        this.excelController = new ExcelController(context);
    }
    public ArrayList<_Location> getLocationData(){
        ArrayList<_Location> locations = new ArrayList<_Location>();
        ArrayList<HashMap<String,String>> excel_arrayList = this.excelController.readExcel(FILE_NAME);
        //excel_arrayList의 원소를 각각 꺼내서(a_data) 각각을 Location 클래스 객체로 맵핑한다.
        excel_arrayList.forEach(a_data -> {
            _Location a_location = new _Location();
            String code = a_data.get(COLUMN_CODE);
            String location = a_data.get(COLUMN_LOCATION);
            String isExist = a_data.get(COLUMN_ISEXIST);

            a_location.setCode(code);
            a_location.setLocation(location);
            //Location 객체의 IsExist는 Boolean으로 저장하기로 정했기 때문에, 아래와 같이 변환해준다.
            if(isExist!=null && isExist.equals("Y")){
                a_location.setIsExist(true);
            }else{
                a_location.setIsExist(false);
            }
            //HashMap -> Location 객체로 맵핑이 끝났으니, 이것을 결과ArrayList에 저장한다.
            locations.add(a_location);
        });

        return locations;
    }
}