package com.example.bustracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;

public class addBus extends AppCompatActivity {

    //    DatabaseReference busDetails;
    TextInputLayout busNo, location, destination, startTime, reachTime;
    Button addBus;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    ProgressBar bar;

    private int LOCATION_REQUEST_CODE = 10001;

    Double latitude,logitude;

    private int mYear, mMonth, mDay, mHour, mMinute;

    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTitle("Add Bus Details");

        busNo = (TextInputLayout) findViewById(R.id.busNo);
        location = (TextInputLayout) findViewById(R.id.Location);
        destination = (TextInputLayout) findViewById(R.id.destination);
        startTime = (TextInputLayout) findViewById(R.id.startTime);
        reachTime = (TextInputLayout) findViewById(R.id.reachTime);
        bar = (ProgressBar) findViewById(R.id.progressBar3);
        addBus = (Button) findViewById(R.id.addBus);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(addBus.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                startTime.getEditText().setText(hourOfDay + ":" + minute);

                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

        reachTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(addBus.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                reachTime.getEditText().setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

            }
        });
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);


        }
        else {
            Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
            locationResult.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location1) {
                    Toast.makeText(addBus.this,"hi "+ location1.getLatitude(),Toast.LENGTH_LONG).show();
                    //sendlocation(( location.getLatitude()),location.getLongitude(), busNumber);

                    latitude = location1.getLatitude();
                    logitude = location1.getLongitude();
                }
            });        }

//        busDetails = FirebaseDatabase.getInstance().getReference().child("busDetails");

//        addBus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {

//                rootNode = FirebaseDatabase.getInstance();
//                reference = rootNode.getReference("BusDetails");

//                String busNumber = busNo.getEditText().getText().toString();
//                String busLocation = location.getEditText().getText().toString();
//                String busDestination = destination.getEditText().getText().toString();
//                String busStartTime = startTime.getEditText().getText().toString();
//                String busReachTime = reachTime.getEditText().getText().toString();

//                UserHelperClass helperClass = new UserHelperClass(busNumber, busLocation, busDestination, busStartTime, busReachTime);
//                reference.child(busNumber).setValue(helperClass);


        //                HashMap<String,Object> map = new HashMap<>();
//                map.put("busNo",busNo.getEditText().getText().toString());
//                map.put("busLocation",location.getEditText().getText().toString());
//                map.put("busDestination",destination.getEditText().getText().toString());
//                map.put("busStartTime",startTime.getEditText().getText().toString());
//                map.put("busReachTime",reachTime.getEditText().getText().toString());
//
//                busDetails.push()
//                        .setValue(map)
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                Log.i("jfbvkj", "onComplete: ");
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Log.i("jfbvkj", "onFailure: "+e.toString());
//                            }
//                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Log.i("jfbvkj", "onSuccess: ");
//                    }
//                });
//

//          }});
    }

    public void Add(View view) {


        bar.setVisibility(View.VISIBLE);
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("BusDetails");

        final String busNumber = busNo.getEditText().getText().toString();
        String busLocation = location.getEditText().getText().toString();
        String busDestination = destination.getEditText().getText().toString();
        String busStartTime = startTime.getEditText().getText().toString();
        String busReachTime = reachTime.getEditText().getText().toString();




                UserHelperClass helperClass = new UserHelperClass(busNumber, busLocation, busDestination, busStartTime, busReachTime,latitude,logitude);
                reference.child(busNumber).setValue(helperClass)
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(addBus.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        bar.setVisibility(View.INVISIBLE);
                        Toast.makeText(addBus.this, "Data added", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(addBus.this,update.class);
                        startActivity(i);

                    }
                });



        // 2nd method using map function
//
//        reference = FirebaseDatabase.getInstance().getReference().child("BusDetails");
//
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("busNo",busNo.getEditText().getText().toString());
//        map.put("busLocation",location.getEditText().getText().toString());
//        map.put("busDestination",destination.getEditText().getText().toString());
//        map.put("busStartTime",startTime.getEditText().getText().toString());
//        map.put("busReachTime",reachTime.getEditText().getText().toString());
//
//        reference.push()
//                .setValue(map)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(addBus.this, "Data Added", Toast.LENGTH_SHORT).show();
//
//                        Intent i = new Intent(addBus.this,update.class);
//                        startActivity(i);
//                    }
//                });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout:
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(), "User Signed Out", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                return true;
            case R.id.addBus:
//                Intent addBus = new Intent(MainActivity.this,addBus.class);
//                startActivity(addBus);
                startActivity(new Intent(getApplicationContext(),addBus.class));
                return true;

            case R.id.showAllBus:
//                Intent showBus = new Intent(MainActivity.this,showBus.class);
//                startActivity(showBus);
                startActivity(new Intent(getApplicationContext(),showBus.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sendlocation(Double Latitude,Double Logitude, String busNumber){


        busLocationDetails coord = new busLocationDetails(Latitude,Logitude);
        reference.child("BusDetails").child("Location").child(busNumber).setValue(coord);
        //reference.child(busNumber).child("Location").setValue(coord);
    }






}
