package com.lasercats;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.BluetoothLeScanner;

public class BluetoothModule extends ReactContextBaseJavaModule {

    private BluetoothManager manager;
    private BluetoothAdapter adapter;
    private ScanCallback scanCallback;
    private BluetoothLeScanner leScanner;

    public BluetoothModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.manager = (BluetoothManager) reactContext.getSystemService(ReactApplicationContext.BLUETOOTH_SERVICE);
        this.adapter = manager.getAdapter();
        this.leScanner = adapter.getBluetoothLeScanner();
    }

    @Override
    public String getName() {
        return "BluetoothModule_Droid";
    }

    @ReactMethod
    public void sayHello( Callback successCallback ) {
        successCallback.invoke("Hello from the Java TestModule!");
        //successCallback.invoke(23+5);i
    }

    @ReactMethod
    public void getAdapterState( Callback success ) {
        if( this.adapter != null ) success.invoke(stateString(this.adapter.getState()));
        else success.invoke("ADAPTER_NULL");
    }

    private String stateString( int state ) {
        switch( state ) {
            case BluetoothAdapter.STATE_DISCONNECTED:
                return "STATE_DISCONNECTED";
            case BluetoothAdapter.STATE_CONNECTING:
                return "STATE_CONNECTING";
            case BluetoothAdapter.STATE_CONNECTED:
                return "STATE_CONNECTED";
            case BluetoothAdapter.STATE_DISCONNECTING:
                return "STATE_DISCONNECTING";
            case BluetoothAdapter.STATE_OFF:
                return "STATE_OFF";
            case BluetoothAdapter.STATE_TURNING_ON:
                return "STATE_TURNING_ON";
            case BluetoothAdapter.STATE_ON:
                return "STATE_ON";
            case BluetoothAdapter.STATE_TURNING_OFF:
                return "STATE_TURNING_OFF";
            default:
                return "STATE_UNKNOWN";
        }
    }
}