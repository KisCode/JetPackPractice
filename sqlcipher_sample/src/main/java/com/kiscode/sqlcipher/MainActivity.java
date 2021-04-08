package com.kiscode.sqlcipher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.kiscode.sqlcipher.util.DBUtil;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteDatabaseHook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void initializeSQLCipher() {
        SQLiteDatabase.loadLibs(this);

        File databaseFile = new File(getCacheDir(), "dict_encrypted.db");
        try {
            InputStream inputStream = getAssets().open("dict_encrypted.db");
            inputstreamtofile(inputStream, databaseFile);
            Log.i("databaseFile", databaseFile.getName() + "\t" + databaseFile.length());

        } catch (IOException e) {
            e.printStackTrace();
        }


        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFile.getAbsolutePath(), "zyw745293", null, new SQLiteDatabaseHook() {
            public void postKey(SQLiteDatabase param1SQLiteDatabase) {
                param1SQLiteDatabase.execSQL("PRAGMA cipher_page_size = 1024");
                param1SQLiteDatabase.execSQL("PRAGMA kdf_iter = 64000");
                param1SQLiteDatabase.execSQL("PRAGMA cipher_hmac_algorithm = HMAC_SHA1");
                param1SQLiteDatabase.execSQL("PRAGMA cipher_kdf_algorithm = PBKDF2_HMAC_SHA1");
            }

            public void preKey(SQLiteDatabase param1SQLiteDatabase) {
            }
        });

/*        String querySql = "select * from classify";
        Cursor cursor = database.rawQuery(querySql, null);
        while (cursor.moveToNext()) {
            int columnCount = cursor.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                String columnName = cursor.getColumnName(i);
                String columnValue = cursor.getString(cursor.getColumnIndex(columnName));
                Log.i("columnCount", columnName + "\t" + columnValue);
            }
        }*/


       /* String querySql = "select * from sqlite_master";
        Cursor cursor = database.rawQuery(querySql, null);
        while (cursor.moveToNext()) {
            int columnCount = cursor.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                String columnName = cursor.getColumnName(i);
                String columnValue = cursor.getString(cursor.getColumnIndex(columnName));
                Log.i("columnCount", columnName + "\t" + columnValue);
            }
        }
*/

        String querySql = "select * from dict";
        Cursor cursor = database.rawQuery(querySql, null);
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        while (cursor.moveToNext()) {
            int columnCount = cursor.getColumnCount();

            StringBuilder inserLine = new StringBuilder();
            if (index % 400 == 0) {
                inserLine.append("INSERT INTO \"dict\" ");
            }

            StringBuilder pararmLine = new StringBuilder("(");
            StringBuilder valueLine = new StringBuilder("(");
            for (int i = 0; i < columnCount; i++) {
//                INSERT INTO "main"."HanZi"("hzId", "Zi", "WuBi", "BuShou", "BiHua", "ShengYuBiHua", "PinYin", "pytext") VALUES (1, '一', 'ggll', '一', 1, 0, 'yī', 'yi1');
                String columnName = cursor.getColumnName(i);
                String columnValue = cursor.getString(cursor.getColumnIndex(columnName));

                if (!TextUtils.isEmpty(columnValue)) {
                    if (columnValue.contains("'")) {
                        Log.i("stringBuilder23", columnValue);
                        columnValue = columnValue.replace("'", "’");
                        Log.i("stringBuilder233", columnValue);
                    }
                    if (columnValue.contains("\"")) {
                        Log.i("stringBuilder22", columnValue);
                        columnValue = columnValue.replace('"', ' ');
                        Log.i("stringBuilder222", columnValue);
                    }
                }
//                Log.i("columnCount", columnName + "\t" + columnValue);
                pararmLine.append("'").append(columnName).append("'");

                if (columnValue != null) {
                    valueLine.append("'").append(columnValue).append("'");
                } else {
                    valueLine.append(columnValue);
                }

                if (i < columnCount - 1) {
                    pararmLine.append(",");
                    valueLine.append(",");
                }
                if (i == columnCount - 1) {
                    pararmLine.append(")");
                    valueLine.append(")");
                }

            }

            if (index % 400 == 0) {
                stringBuilder.append(inserLine).append(pararmLine).append(" ").append("VALUES").append(valueLine);
            } else {
                stringBuilder.append(valueLine);
            }

            if ((index + 1) % 400 == 0) {
                stringBuilder.append(";\n");
            } else {
                stringBuilder.append(",\n");
            }
            index++;
        }

//        Log.i("stringBuilder", stringBuilder.toString());

        writeToCache(stringBuilder.toString());


    }

    private void writeToCache(String toString) {
        File file = new File(getCacheDir(), "sql7.txt");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(toString);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inputstreamtofile(InputStream ins, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        ins.close();
    }

    public void load(View view) {
        initializeSQLCipher();
    }
}