package com.example.next.absolutepathine;

import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;

/**
 * Created by next on 10/3/17.
 */
public class StorageDetails extends Fragment {
    private static String TAG = "StorageDetails";
    private TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_storage_details, container, false);
        textView = (TextView) view.findViewById(R.id.txt_results);
        String data = getArguments().getString(ConstantsValues.BUNDLE_KEY);
        Log.i(TAG, "onCreateView: "+data);


if (data != null) {
    switch (data) {
        case ConstantsValues.INTERNAL_PATH:
             textView.setText(getInternalPath());
            break;
        case ConstantsValues.EXTERNAL_PATH:
            String details = getPathSDcard();
            if (details.equalsIgnoreCase("null")) {
                textView.setText("Not found SD card");
                break;
            }
            textView.setText(getPathSDcard());
            break;
        case ConstantsValues.INTENAL_SIZE:
            textView.setText(phoneStorageFree());
            break;
        case ConstantsValues.EXTENAL_SIZE:
            String detailsSize = sdCardFree();
            if (detailsSize.equalsIgnoreCase("null")) {
                textView.setText("Not found SD card");
                break;
            }
            textView.setText(sdCardFree());

    }
}

        return view;



    }

    //internal path
    private String getInternalPath() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            String root = Environment.getExternalStorageDirectory().getAbsolutePath();
            return root;
        }
        return null;
    }

    //SD card path
    private String getPathSDcard() {
        String storagePath = System.getenv("SECONDARY_STORAGE");

        if (storagePath == null) {
            return "null";
        } else {
            String[] storagePathArray = storagePath.split(":");
            return storagePathArray[0];

        }
    }

    // PHONE STORAGE
    public  String phoneStorageFree(){
        String  detalis= "";
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(getInternalPath());
        long free_memory = stat.getAvailableBlocksLong() * stat.getBlockSizeLong(); //return value is in bytes
        long phone_storage_used = (stat.getBlockCountLong() - stat.getAvailableBlocksLong()) * stat.getBlockSizeLong(); //return value is in bytes
        long phone_storage_total = stat.getBlockCountLong() * stat.getBlockSizeLong(); //return value is in bytes
        detalis = detalis+"   Free Memory:  "+free_memory/1000000+"MB \n"+"phone storage used:  "+phone_storage_used/1000000+"MB \n"+"phone storage total:  "
                                        +phone_storage_total/1000000+"MB\n";
        return detalis;
    }




    // SD CARD
    public  String sdCardFree(){
        String  detalis= "";
        String path = getPathSDcard();
        if (!path.equalsIgnoreCase("null")) {
            StatFs stat = new StatFs(path);
            long free_memory = stat.getAvailableBlocksLong() * stat.getBlockSizeLong(); //return value is in bytes
            long sd_card_used = (stat.getBlockCountLong() - stat.getAvailableBlocksLong()) * stat.getBlockSizeLong(); //return value is in bytes
            long sd_card_total = stat.getBlockCountLong() * stat.getBlockSizeLong(); //return value is in bytes
            detalis = detalis+"   Free Memory:  "+free_memory/1000000+"MB \n"+"SD storage used:  "+sd_card_used/1000000+"MB \n"+"SD storage total:  "
                    +sd_card_total/1000000+"MB \n";
            return detalis;
        }
       return path;
    }

    public String createFile(){
        return null;
    }
}
