package com.example.gps_test4.model;

public class _Location {

    // [행정코드] [행정구역명] [여부] 로 이루어진 데이터를 객체화 할 Model(_Location) Class

    
    // TODO 숙제3 : 자바 클래스와 멤버 변수, 멤버 함수에 대해 공부하고 아래 코드에서 모르고 있는 것들에 표시
    // TODO 숙제4 : 프라이빗과 퍼블릭 키워드의 차이를 공부해서 적기 
    // TODO 숙제5 : 숙제 1,2,3,4 에서 배운 것을 바탕으로 모든 코드 줄에 주석으로 설명 달기
    private String code;        // 행정코드를 담음
    private String location;    // 행정구역명을 담음
    private Boolean isExist;    // 여부를 담음

    public Boolean getExist(){
        return isExist;
    }
    public String getCode(){
        return code;
    }
    public String getLocation(){
        return location;
    }

    public void setCode(String code){
        this.code = code;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public void setIsExist(Boolean isExist){
        this.isExist = isExist;
    }
}
