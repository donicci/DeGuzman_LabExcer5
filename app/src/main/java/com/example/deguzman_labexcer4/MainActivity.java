package com.example.deguzman_labexcer4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] android, version, level, rdate, dbmsg;
    ListView list;
    int[] cLogo = {R.drawable.cupcake, R.drawable.donut,R.drawable.eclair,R.drawable.froyo, R.drawable.gingerbread,R.drawable.honeycomb, R.drawable.icecreamsandwich,
            R.drawable.jellybean, R.drawable.kitkat, R.drawable.lollipop, R.drawable.marshmallow, R.drawable.nougat, R.drawable.oreo, R.drawable.androidpie, R.drawable.androidten};

    ArrayList<Android> androidList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android = getResources().getStringArray(R.array.android);
        version = getResources().getStringArray(R.array.version);
        level = getResources().getStringArray(R.array.level);
        dbmsg = getResources().getStringArray(R.array.dbmsg);
        rdate = getResources().getStringArray(R.array.rdate);
        list = findViewById(R.id.lvColleges);
        for(int i = 0; i < android.length; i++){
            androidList.add(new Android(cLogo[i], android[i], level[i], rdate[i], version[i], dbmsg[i]));
        }
        list = findViewById(R.id.lvColleges);
        AndroidAdapter adapter = new AndroidAdapter(this, R.layout.item ,androidList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

        try {


        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        final File folder = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder, "AndroidVersion.txt");
        FileOutputStream fos = new FileOutputStream(file);
        String choice = "Version Name: " + androidList.get(i).getAndroid() + "\n " + androidList.get(i).getRdate();
        fos.write(choice.getBytes());
        fos.close();
        dialog.setTitle(androidList.get(i).getAndroid());
        dialog.setIcon(androidList.get(i).getLogo());
        dialog.setMessage(androidList.get(i).getDbmsg());
        dialog.setNeutralButton("CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick (DialogInterface dialog,int which){
                dialog.dismiss();
                Toastt();
            }


        });
        dialog.create().show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void Toastt() {
        try {
            FileInputStream fin;
            fin = new FileInputStream(new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/AndroidVersion.txt"));
            int i;
            String str = "";
            while ((i = fin.read()) != -1) {
                str += Character.toString((char) i);
            }
            fin.close();
            Toast.makeText(this, str, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
