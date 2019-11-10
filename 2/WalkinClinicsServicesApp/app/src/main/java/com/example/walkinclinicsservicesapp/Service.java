package com.example.walkinclinicsservicesapp;

import androidx.appcompat.app.AppCompatActivity;

public class Service extends AppCompatActivity implements IService {

    private String name;
    private String role;

    public Service(String name, String role){
        this.name=name;
        this.role = role;
    }

    @Override
    public String getServiceName() {
        return this.name;
    }

    @Override
    public String getEmployeeRole(){
        return this.role;
    }

    public String setServiceName(String name){
        return this.name=name;
    }

    public String setEmployeeRole(String employeeRole){
        return this.role=employeeRole;
    }



}
