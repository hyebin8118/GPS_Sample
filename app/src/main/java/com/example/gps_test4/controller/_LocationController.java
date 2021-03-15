package com.example.gps_test4.controller;

import android.content.Context;

import com.example.gps_test4.model.LocationData;
import com.example.gps_test4.model._Location;

import java.util.ArrayList;
import java.util.HashMap;

public class _LocationController {

    // 행정코드 파일
    final String FILE_NAME_ADMINISTRATIVE = "administrative_code.xls";
    final String COLUMN_CODE_ADMINISTRATIVE = "행정코드";
    final String COLUMN_LOCATION_ADMINISTRATIVE = "행정구역명";
    final String COLUMN_ISEXIST_ADMINISTRATIVE = "여부";

    // 법정코드 파일
    final String FILE_NAME_COURT = "court_code.xls";
    final String COLUMN_CODE_COURT = "법정동코드";
    final String COLUMN_LOCATION_COURT = "법정동명";
    final String COLUMN_ISEXIST_COURT = "여부";

    ExcelController excelController;

    public _LocationController(Context context){
        this.excelController = new ExcelController(context);

        ArrayList<LocationData> administrativeFile = excelController.administrative_readExcel(FILE_NAME_ADMINISTRATIVE);
        ArrayList<LocationData> courtFile = excelController.court_readExcel(FILE_NAME_COURT, administrativeFile);
    }
}