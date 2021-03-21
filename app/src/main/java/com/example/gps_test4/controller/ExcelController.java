package com.example.gps_test4.controller;

import android.content.Context;
import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import jxl.Sheet;
import jxl.Workbook;

public class ExcelController {
    Context context;
    public ExcelController(){}

    public ExcelController(Context context){
        this.context = context;
    }
    public ArrayList readExcel(String fileName){
        ArrayList resultArrayList = new ArrayList<HashMap<String,String>>();
        try{
            InputStream inputStream = context.getResources().getAssets().open(fileName); //`fileName`의 이름을 가진 엑셀 파일을 '연다'
            Workbook workbook = Workbook.getWorkbook(inputStream);

            if(workbook!=null){

                Sheet sheet = workbook.getSheet(0);

                if(sheet !=null){
                    int colTotal = sheet.getColumns();
                    int rowTotal = sheet.getColumn(colTotal-1).length;
                    ArrayList<String> fieldNameList = new ArrayList<String>();

                    Log.d("EXCEL_CONTROLLER:col:", colTotal +"");
                    Log.d("EXCEL_CONTROLLER:row:", rowTotal +"");

                    for(int data_index=0; data_index<rowTotal; data_index++){
                        HashMap a_data = new HashMap<String,String>();
                        for(int field_index=0; field_index<colTotal; field_index++){
                            //(column, row) 좌표의 셀의 값을 가져온다.
                            String cellValue = sheet.getCell(field_index, data_index).getContents();
                            //Log.d("EXCEL_CONTROLLER:", cellValue);
                            if(data_index==0){
                                fieldNameList.add(cellValue);
                            }else{
                                String fieldName = fieldNameList.get(field_index);
                                a_data.put(fieldName, cellValue);
                                //a_data.put(fieldNameList.get(field_index), cellValue); 이렇게 써도 위와 동일한 작동을 한다.
                            }
                            //결과를 저장할 arrayList에 HashMap객체를 저장(.add()함수로)한다.
                        }
                        if(data_index!=0) resultArrayList.add(a_data);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d("EXCEL_CONTROLLER:", e.toString());
        }
        return resultArrayList;
    }
}