package com.example.walkinclinicsservicesapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.net.sip.SipSession;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManageServices extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button addServiceButton, addNewServiceButton;
    ListView listViewServices;
    List<Service> services;
    Spinner spinner;
    private static final String[] employeeRole ={ "Staff", "Doctor","Nurse"};

    private String  empRole;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manage_services);

        listViewServices= (ListView)  findViewById(R.id.listViewServices);

        spinner = (Spinner)findViewById(R.id.spinnerEmployeeRole);

        addServiceButton = (Button)findViewById(R.id.addServiceButton);

        listViewServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Service service = services.get(i);
                showUpdateDeleteDialog(service.getServiceName(), service.getEmployeeRole());
                return true;
            }
        });

    }





    @Override
    protected void onStart() {
        super.onStart();

        DBHelper dbServices = new DBHelper(this);
        services=dbServices.getAllServices();

        //creating adapter
        ServiceList serviceAdapter = new ServiceList(ManageServices.this, services);

        //attaching adapter to the listview
        listViewServices.setAdapter(serviceAdapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                this.empRole= employeeRole[0];
                break;
            case 1:
                this.empRole= employeeRole[1];
                break;
            case 2:
                this.empRole= employeeRole[2];
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }


    public void addService(View view){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_service_dialogue, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextNewServiceName = (EditText) dialogView.findViewById(R.id.editTextNewServiceName);

        spinner  = (Spinner) dialogView.findViewById(R.id.spinnerEmployeeRole);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ManageServices.this, android.R.layout.simple_spinner_item,employeeRole);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        final Service service = new Service(editTextNewServiceName.getText().toString(),empRole);

        addNewServiceButton = (Button) dialogView.findViewById(R.id.buttonAddNewService);


        dialogBuilder.setTitle("Create New Service");
        final AlertDialog b = dialogBuilder.create();
        b.show();

        addNewServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serviceName = editTextNewServiceName.getText().toString();
                if(!serviceName.isEmpty()){
                    DBHelper db = new DBHelper(getApplicationContext());
                    db.createService(editTextNewServiceName.getText().toString(),empRole);
                    b.dismiss();
                    onStart();
                }
                //ceck to see if the service name and role already exist
                else if(false){

                }
                else{
                    Toast.makeText(ManageServices.this,"Service not created.\nGive service a name",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void showUpdateDeleteDialog(final String serviceName, final String role){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.layout_delete_service, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextServiceName = (EditText) dialogView.findViewById(R.id.editTextServiceName);
        final EditText editTextRole  = (EditText) dialogView.findViewById(R.id.editTextRole);


        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteService);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateService);
        dialogBuilder.setTitle(serviceName);
        final AlertDialog b = dialogBuilder.create();
        b.show();


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(getApplicationContext());
                db.deleteService(serviceName,role);
                b.dismiss();
                onStart();
                Toast.makeText(ManageServices.this,"Service deleted.",Toast.LENGTH_SHORT).show();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db =new DBHelper(getApplicationContext());
                String newServiceName = editTextServiceName.getText().toString();
                String newRole = editTextRole.getText().toString();
                if(newServiceName.isEmpty() || newRole.isEmpty()){
                    db.updateService(serviceName,role,newServiceName,newRole);
                    b.dismiss();
                    onStart();
                }
                else{
                    Toast.makeText(ManageServices.this,"Service not updated.\nDon't leave blank.",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
