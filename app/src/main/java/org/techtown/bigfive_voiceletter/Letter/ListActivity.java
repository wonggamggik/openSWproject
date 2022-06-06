package org.techtown.bigfive_voiceletter.Letter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;
import org.techtown.bigfive_voiceletter.R;

import java.util.Collection;
import java.util.Iterator;

public class ListActivity extends AppCompatActivity {
    private int REQUEST_TEST = 200;

    //Button write_btn;
    org.techtown.bigfive_voiceletter.Letter.PreferenceManager pref;
    RecyclerView recyclerView;
    org.techtown.bigfive_voiceletter.Letter.MemoAdapter memoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        pref = new org.techtown.bigfive_voiceletter.Letter.PreferenceManager();

        //write_btn = findViewById(R.id.write_btn);
        //write_btn.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
       //         Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
       //         startActivityForResult(intent,REQUEST_TEST);
        //    }
       // });

        //리사이클러뷰 세팅
        LinearLayoutManager linearLayoutManager;
        recyclerView = findViewById(R.id.memo_rv);//리사이클러뷰 findView
        linearLayoutManager = new LinearLayoutManager(ListActivity.this, LinearLayoutManager.VERTICAL, false);

        memoAdapter = new org.techtown.bigfive_voiceletter.Letter.MemoAdapter(ListActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);//linearlayout 세팅
        recyclerView.setAdapter(memoAdapter);//adapter 세팅

        //쉐어드 모든 키 벨류 가져오기
        SharedPreferences prefb =getSharedPreferences("memo_contain", MODE_PRIVATE);
        Collection<?> col_val =  prefb.getAll().values();
        Iterator<?> it_val = col_val.iterator();
        Collection<?> col_key = prefb.getAll().keySet();
        Iterator<?> it_key = col_key.iterator();

        while(it_val.hasNext() && it_key.hasNext()) {
            String key = (String) it_key.next();
            String value = (String) it_val.next();
            try {
                JSONObject jsonObject = new JSONObject(value);
                String title = (String) jsonObject.getString("title");
                String content = (String) jsonObject.getString("content");
                String reserve = (String) jsonObject.getString("selectedDate");
                memoAdapter.addItem(new org.techtown.bigfive_voiceletter.Letter.MemoItem(key, title, content, reserve));
            } catch (JSONException e) {
                Log.d("MainActivity","JSONObject : "+e);
            }

            memoAdapter.notifyDataSetChanged();

        }
        //pref.clear(MainActivity.this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TEST) {
            if (resultCode == RESULT_OK) {

                Intent intent = getIntent();
                String get_date = data.getStringExtra("date");
                String get_title = data.getStringExtra("title");
                String get_content = data.getStringExtra("content");
                String get_reserve = data.getStringExtra("selectedDate");

                memoAdapter.addItem(new org.techtown.bigfive_voiceletter.Letter.MemoItem(get_date,get_title,get_content, get_reserve));

                memoAdapter.notifyDataSetChanged();
                Toast.makeText(ListActivity.this, "작성 되었습니다", Toast.LENGTH_SHORT).show();

            } else {   // RESULT_CANCEL
                Toast.makeText(ListActivity.this, "저장 되지 않음", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void loadMemo() {
        // 쉐어드 모든 키 벨류 가져오기
        SharedPreferences prefb = getSharedPreferences("memo_contain",MODE_PRIVATE);
        Collection<?> col_val = prefb.getAll().values();
        Iterator<?> it_val = col_val.iterator();
        Collection<?> col_key = prefb.getAll().keySet();
        Iterator<?> it_key = col_key.iterator();

        while(it_val.hasNext() && it_key.hasNext()) {
            String key = (String) it_key.next();
            Log.d("Result",key);
            String value = (String) it_val.next();
            Log.d("Result",value);

            try {
                JSONObject jsonObject = new JSONObject(value);
                String title = (String) jsonObject.getString("title");
                String content = (String) jsonObject.getString("content");
                String reserve = (String) jsonObject.getString("selectedDate");
                memoAdapter.addItem(new org.techtown.bigfive_voiceletter.Letter.MemoItem(key,title,content, reserve));
            } catch (JSONException e){

            }

            memoAdapter.notifyDataSetChanged();
        }
    }
}
