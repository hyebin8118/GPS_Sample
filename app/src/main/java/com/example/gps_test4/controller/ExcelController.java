package com.example.gps_test4.controller;

import android.content.Context;
import android.util.Log;

import com.example.gps_test4.model.City;
import com.example.gps_test4.model.Dong;
import com.example.gps_test4.model.Gu;
import com.example.gps_test4.model.LocationData;

import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import jxl.Sheet;
import jxl.Workbook;

public class ExcelController {
    Context context;
    public ExcelController(){}
    /*
    resultArrayList에는 city만 들어가도록 할 것
    city 다음에는 그 시에 해당하는 구가 나오고
    구 다음에는 그 구에 해당하는 동이 나온다.
    마지막 city에 gu를 붙여주고 마지막 gu에 동을 붙여준다.
    동이 끝나면 구가 시작되는데, 이 구 또한 마지막 시에 붙여준다.
    guList, dongList
    */
    public ExcelController(Context context){
        this.context = context;
    }

    public ArrayList administrative_readExcel(String fileName) {
        ArrayList resultArrayList = new ArrayList();

        try {
            // fileName 은 ExcelController 에서 읽어올 파일으로 초기화 시켜줌
            InputStream inputStream = context.getResources().getAssets().open(fileName);

            Workbook workbook = Workbook.getWorkbook(inputStream);

            // Excel 파일이 있다면
            if(workbook != null){
                // 파일의 첫번째 시트를 불러옴
                Sheet sheet = workbook.getSheet(0);

                // 시트가 존재한다면
                if(sheet != null){

                    // Column 열
                    int colTotal = sheet.getColumns();
                    // Row 행 (int rowTotal = sheet.getRows(); 로 사용해도 무관하다.)
                    int rowTotal = sheet.getColumn(colTotal-1).length;

                    // 행(가로)을 반복해서 읽음
                    for(int row_index = 0; row_index < rowTotal; row_index++){

                        String tempValue = sheet.getCell(0, row_index).getContents();
                        LocationData locationData_aObject;
                        Log.d("tempValue.length"," "+tempValue.length());
                        Log.d("tempValue"," "+tempValue);


                        switch(tempValue.length()){
                            case 2 :
                                locationData_aObject = new City();
                                locationData_aObject.setAdministrative_code(tempValue);
                                break;
                            case 5 :
                                locationData_aObject = new Gu();
                                locationData_aObject.setAdministrative_code(tempValue);
                                break;
                            case 7 :
                                locationData_aObject = new Dong();
                                locationData_aObject.setAdministrative_code(tempValue);
                                break;
                            default :
                                if(row_index==0) continue;
                                Log.e("Switch","code 길이 오류 발생");
                                return null;
                        }

                        tempValue = sheet.getCell(1, row_index).getContents();
                        locationData_aObject.setAdministrative_locationName(tempValue);
                        /*for(int column_index = 1; column_index < colTotal; column_index++){
                            // 좌표의 셀의 값을 가져옴
                            // getCell = 셀을 가져온다.
                            String cellValue = sheet.getCell(column_index, row_index).getContents();
                            //Log.d("Before cellValue", " "+cellValue);

                            // 0번째 행이라면 - 첫번째 행은 필드 이름
                            if(row_index == 0){
                                fieldNameList.add(cellValue);
                            }
                            // 0번째 행이 아니라면(행정구역명)
                            else {
                                // String fieldName = fieldNameList.get(column_index);
                                // a_data.put(fieldName, cellValue); //cellValue - 데이터를 저장하는 임시
                            }
                        }*/
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("EXCEL_CONTROLLER : ", e.toString());
        }
        return resultArrayList;
    }

    public ArrayList court_readExcel(String fileName, ArrayList resultArrayList){
        try{
            InputStream inputStream = context.getResources().getAssets().open(fileName);
            Workbook workbook = Workbook.getWorkbook(inputStream);

            if(workbook!=null){
                Sheet sheet = workbook.getSheet(0);

                if(sheet!=null){
                    int rowTotal = sheet.getRows();

                    for(int row_index = 0; row_index<rowTotal; row_index++){
                        String tempValue = sheet.getCell(0, row_index).getContents();

                        int count_tempValue = StringUtils.countMatches(tempValue,"0");
                        LocationData temp_locationData;
                        Log.d("resultArrayList"," "+resultArrayList.get(row_index));

                        // city - 코드 안의 "0"의 개수가 8과 같거나 더 많은 경우
                        if(row_index != 0 && count_tempValue >= 8){
                            temp_locationData = (LocationData) resultArrayList.get(0);
                            temp_locationData.setCourt_code(tempValue);
                        }
                        // gu
                        else if(row_index!=0 && count_tempValue >= 6){
                            temp_locationData = (LocationData) resultArrayList.get(1);
                            temp_locationData.setCourt_code(tempValue);
                        }
                        // dong
                        else if(row_index != 0 && count_tempValue >= 4){
                            temp_locationData = (LocationData)resultArrayList.get(2);
                            temp_locationData.setCourt_code(tempValue);
                        } else {
                            Log.e("court", "Error");

                            return null;
                        }
                        tempValue = sheet.getCell(1, row_index).getContents();
                        temp_locationData.setCourt_locationName(tempValue);
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            Log.e("court_readExcel"," Error");
        }
        return resultArrayList;
    }
}