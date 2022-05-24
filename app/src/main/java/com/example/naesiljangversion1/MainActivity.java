package com.example.naesiljangversion1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String val = "";

    RecipeConnection myDB;
    ArrayList<String> fridge_ingredients, fridge_type, fridge_date;
    ArrayList<String> recipe_name, recipe_info, recipe_image, recipe_star;
    ArrayList<String> star_name, star_info, star_image, star_star, star_sql;
    ListView fridge_listView, recipe_listView, star_listView;
    /* ListViewAdapter fridge_adapter;*/
    TextView myFridge;
    Bitmap bmp;
    int count = 0, count2 = 0, view_type = 0;
    EditText inputShop;
    Button addShop;

    /*ArrayList<String> recipe_name, recipe_info, recipe_image, recipe_star;*/
    Button buttonView;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new RecipeConnection(this);
        Cursor cursor=myDB.readAllData();

        if(cursor.getCount()>0){
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();

        }

       /* getVal();*/

    }
    public void getVal() {

        RecipeConnection dbHelper = new  RecipeConnection(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM recipe",null);
        //" and name = ?",new String[]{"홍길동"});
        while (cursor.moveToNext())
        {
            val += cursor.getString(2)+", ";

        }
        /*sqlresult.setText("재료: "+val);*/
        cursor.close();
        dbHelper.close();
    }

}
    /*public void viewAll()
    {
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.readAllData();
                if(res.getCount() == 0){
                    ShowMessage("실패","데이터를 찾을 수 없습니다.");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("이름: "+res.getString(1)+"\n");
                }
                ShowMessage("데이터",buffer.toString());
            }
        });}
        public void ShowMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    }*/
    /*public void getVal() {
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        StarDatabaseHelper dbHelper2 = new StarDatabaseHelper(this);
        SQLiteDatabase db2 = dbHelper2.getReadableDatabase();
        int star=0;
        String[] strData=fridge_ingredients.toArray(new String[fridge_ingredients.size()]);
        recipe_name=new ArrayList<>();
        recipe_info=new ArrayList<>();
        recipe_image=new ArrayList<>();
        recipe_star=new ArrayList<>();
        if(strData.length>0){
            //현재 냉장고 안에 있는 재료 출력
            String addIngredients=strData[0];
            for(int i=1;i<strData.length;i++){
                addIngredients+=", "+strData[i];
            }
            myFridge.setText("재료: "+addIngredients);
            // 냉장고 속 식재료로 만들 수 있는 레시피 조회(가장 많이 겹치는 상위 10개 출력)
            String addSql="A.IRDNT_NM=? ";
            for(int i=0;i<strData.length-1;i++){
                addSql+="OR A.IRDNT_NM=? ";
            }
            Cursor cursor = db.rawQuery("SELECT B.RECIPE_NM_KO, B.SUMRY, B.IMG_URL, C.RECIPE_COUNT" +
                    " FROM recipe_basic B,(SELECT A.RECIPE_ID, count(A.IRDNT_NM) AS RECIPE_COUNT" +
                    " FROM recipe_ingredient A" +
                    " WHERE " +addSql+
                    " GROUP BY A.RECIPE_ID" +
                    " ORDER BY count(A.IRDNT_NM) DESC) C" +
                    " WHERE B.RECIPE_ID=C.RECIPE_ID" +
                    " ORDER BY C.RECIPE_COUNT DESC limit 10", strData);
            while (cursor.moveToNext())
            {
                val += cursor.getString(0)+", ";
                recipe_name.add(cursor.getString(0));
                recipe_info.add(cursor.getString(1));
                recipe_image.add(cursor.getString(2));
                Cursor cursor2 = db2.rawQuery("SELECT ID" +
                        " FROM my_star" +
                        " WHERE ID =?;", new String[]{cursor.getString(0)});
                if(cursor2.moveToNext()){
                    recipe_star.add("1");
                }else{
                    recipe_star.add("0");
                }
                cursor2.close();
            }
            //namename.setText("요리 목록: "+val);
            cursor.close();
        }
        displayRecipe(recipe_name, recipe_info, recipe_image, recipe_star, 1);
        dbHelper2.close();
        dbHelper.close();
    }*/

