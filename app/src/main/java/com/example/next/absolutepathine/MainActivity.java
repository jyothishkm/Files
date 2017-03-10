package com.example.next.absolutepathine;

import android.app.Dialog;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
 private Button mInternalPath, mExternalPath, mInternalStorage, mExternalStorage,
    mCreateFile, mDeleteFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();




    }

    private void initView() {
        mInternalPath = (Button) findViewById(R.id.btn_internal);
        mExternalPath = (Button) findViewById(R.id.btn_external);
        mInternalStorage = (Button) findViewById(R.id.btn_internal_memory);
        mExternalStorage = (Button) findViewById(R.id.btn_External_memory);
        mCreateFile = (Button) findViewById(R.id.btn_create_file);
        mDeleteFile = (Button) findViewById(R.id.btn_delete_file);

        mInternalPath.setOnClickListener(this);
        mExternalPath.setOnClickListener(this);
        mInternalStorage.setOnClickListener(this);
        mExternalStorage.setOnClickListener(this);
        mCreateFile.setOnClickListener(this);
        mDeleteFile.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        Bundle bundle = null;
        if (bundle == null) {
            bundle = new Bundle();
        }

        Fragment fragment = null;
        if (fragment == null) {
            fragment = new StorageDetails();
        }
        switch (id) {
            case R.id.btn_internal:

                bundle.putString(ConstantsValues.BUNDLE_KEY, ConstantsValues.INTERNAL_PATH);
                break;
            case R.id.btn_external:
                bundle.putString(ConstantsValues.BUNDLE_KEY, ConstantsValues.EXTERNAL_PATH);
                break;
            case R.id.btn_internal_memory:
                bundle.putString(ConstantsValues.BUNDLE_KEY, ConstantsValues.INTENAL_SIZE);
                break;
            case R.id.btn_External_memory:
                bundle.putString(ConstantsValues.BUNDLE_KEY, ConstantsValues.EXTENAL_SIZE);
                break;
            case R.id.btn_create_file:
                    popupDialog();
               // bundle.putString(ConstantsValues.BUNDLE_KEY, ConstantsValues.CREATE_FILE);
                break;
            case R.id.btn_delete_file:
                bundle.putString(ConstantsValues.BUNDLE_KEY, ConstantsValues.DELETE_FILE);
                break;

        }
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).addToBackStack("").commit();
    }

    private void popupDialog() {
         RadioGroup radioGroup;
         RadioButton radioButton;
         Button btnOk;
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_create_file);


        dialog.setTitle("Create New File");
        dialog.show();
    }
}
