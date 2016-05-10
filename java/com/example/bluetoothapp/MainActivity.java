package com.example.bluetoothapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3, b4;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        BA = BluetoothAdapter.getDefaultAdapter();
        lv = (ListView)findViewById(R.id.listView);

        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                turn_on();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                turn_off();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                list();
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                visible();
            }
        });

    }

    public void turn_on(){
        if (!BA.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(getApplicationContext(),"Bluetooth turned ON",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Bluetooth is already ON", Toast.LENGTH_LONG).show();
        }
    }

    public void turn_off(){
        if (BA.isEnabled()) {
            BA.disable();
            Toast.makeText(getApplicationContext(),"Bluetooth Turned off" ,Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Bluetooth is OFF already", Toast.LENGTH_LONG).show();
        }
    }

    public void list() {
        if (!BA.isEnabled()) {
            Toast.makeText(getApplicationContext(), "Please turn ON bluetooth first.", Toast.LENGTH_SHORT).show();
        }
        else {
            pairedDevices = BA.getBondedDevices();
            ArrayList list = new ArrayList();
            for (BluetoothDevice bt : pairedDevices)
                list.add(bt.getName());
            final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
            lv.setAdapter(adapter);
        }
    }

    public void visible(){
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }

}
