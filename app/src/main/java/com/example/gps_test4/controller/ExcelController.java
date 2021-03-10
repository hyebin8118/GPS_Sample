package com.example.gps_test4.controller;

import android.content.Context;
import android.util.Log;

import com.example.gps_test4.model.City;
import com.example.gps_test4.model.Dong;
import com.example.gps_test4.model.Gu;
import com.example.gps_test4.model.LocationData;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import jxl.Sheet;
import jxl.Workbook;

public class ExcelController {

    // 엑셀 파일에서 데이터를 읽어올 때 시 - 시에 해당하는 구 - 그 구에 해당하는 동
    // 더이상 그 구에 해당하는 동이 없을 경우에는 시에 해당하는 다음 구로 넘어가 반복
    // 더이상 그 시에 해당하는 구가 없을 경우에는 다음 시로 넘어감

    Context context;
    public ExcelController(){}

    public ExcelController(Context context){
        this.context = context;
    }

    public ArrayList readExcel(String fileName) {
        ArrayList resultArrayList = new ArrayList<HashMap<String, String>>();

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

                    // 첫번째(index 0) 행을 담을 ArrayList (행정 코드, 행정 구역명, 여부)
                    ArrayList<String> fieldNameList = new ArrayList<>();

                    // 행(가로)을 반복해서 읽음
                    for(int row_index = 0; row_index < rowTotal; row_index++){
                        // HashMap 을 이곳에서 생성한 이유
                        // ㄴ column 을 반복해서 읽는 반복문 내부에 생성하면 column 이 증가할 때마다 HashMap 이 새로 생성된다.
                        HashMap a_data = new HashMap<String, String>();

                        // column_index 0을 먼저 읽는다.
                        String tempValue = sheet.getCell(0, row_index).getContents();

                        LocationData locationData_child;

                        switch(tempValue.length()){
                            case 2 :
                                locationData_child = new City();
                                locationData_child.setCode(tempValue);
                                break;
                            case 5 :
                                locationData_child = new Gu();
                                locationData_child.setCode(tempValue);
                                break;
                            case 7 :
                                locationData_child = new Dong();
                                locationData_child.setCode(tempValue);
                                break;
                            default :
                                Log.e("Switch","code 길이 오류 발생");
                                return null;
                        }

                        tempValue = sheet.getCell(1, row_index).getContents();

                        // switch - default 에 return 값이 없으면 오류가 난다.
                        locationData_child.setLocation_name(tempValue);

                        Log.d("LocationData_child"," get Code :"+locationData_child.getCode());
                        Log.d("LocationData_child", " get location_name : "+locationData_child.getLocation_name());
                        // 열(세로)을 반복해서 읽음 -- 1행 2행…
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

                        // 결과를 저장할 resultArrayList에 HashMap 객체를 저장
                        if(row_index != 0){
                            resultArrayList.add(a_data);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("EXCEL_CONTROLLER : ", e.toString());
        }
        return resultArrayList;
    }
}