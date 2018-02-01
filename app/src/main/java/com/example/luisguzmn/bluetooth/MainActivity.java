package com.example.luisguzmn.bluetooth;

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

    Button b_on, b_off, b_disc, b_list;
    ListView list;
    BluetoothAdapter bluetoothAdapter;
    private static final int REQUEST_ENABLED = 0;
    private static final int REQUEST_DISCOVERABLE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_on = (Button)findViewById(R.id.on);
        b_off = (Button)findViewById(R.id.off);
        b_disc = (Button)findViewById(R.id.button1);
        b_list = (Button)findViewById(R.id.list);

        list = (ListView)findViewById(R.id.viewlist);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(bluetoothAdapter == null){

            Toast.makeText(this,"Bluetooth not supported",Toast.LENGTH_LONG).show();
        }

        b_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intent,REQUEST_ENABLED);
            }
        });
        b_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetoothAdapter.disable();
            }
        });
        b_disc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bluetoothAdapter.isDiscovering()){
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(intent,REQUEST_DISCOVERABLE);
                }
            }
        });
        b_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

                ArrayList<String> devices = new ArrayList<String>();

                for(BluetoothDevice bt : pairedDevices){
                    devices.add(bt.getName());
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,devices);
                list.setAdapter(arrayAdapter);
            }
        });
    }
}
