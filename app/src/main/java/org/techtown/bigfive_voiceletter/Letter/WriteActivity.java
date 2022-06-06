package org.techtown.bigfive_voiceletter.Letter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import java.util.Calendar;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.techtown.bigfive_voiceletter.MainActivity;
import org.techtown.bigfive_voiceletter.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteActivity extends AppCompatActivity {

    org.techtown.bigfive_voiceletter.Letter.PreferenceManager pref;
    Button save_btn;
    CheckBox checkBox;
    EditText title;
    EditText content;
    LinearLayout reserve;

    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        pref = new org.techtown.bigfive_voiceletter.Letter.PreferenceManager();

        save_btn = findViewById(R.id.save_btn);
        // editText 할당
        title = findViewById(R.id.memo_title_edit);
        content = findViewById(R.id.memo_content_edit);
        checkBox = findViewById(R.id.checkBox);
        //content_line = findViewById(R.id.content_line);
        reserve = findViewById(R.id.reserve);   // 뷰 자체 나타나게 하기
        calendarView = findViewById(R.id.calendarView); // 캘린더 날짜

        final CharSequence[] reserve_date = {null};

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 쓰는 값들이 null이면 저장 버튼 작동안함
                if (title == null || content == null || reserve_date[0] == null) {
                    return;
                }

                // 저장 버튼을 눌러
                // 작성한 editText를 저장
                String edit_title = title.getText().toString();
                String edit_content = content.getText().toString();
                String reserve_txt = reserve_date[0].toString();

                // String 값을 JSONObject로 변환하여 사용할 수 있도록 메모의 제목과 타이틀을 JSON 형식로 저장
                // String save_form = "{\"title\":\"" + edit_title + "\",\"content\":\"" + edit_content + "\"}";

                // key값이 겹치지 않도록 현재 시간으로 부여
                long now = System.currentTimeMillis();
                Date mDate = new Date(now);
                SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String getTime = simpleDate.format(mDate).toString();
                // reserve 값도 위와 같이 실시
                // String save_reserve = "{\"calendarView\":\"" + calendarView + "\"}";

                String save_form = "{\"title\":\"" + edit_title + "\",\"content\":\"" + edit_content +
                        "\",\"selectedDate\":\"" + reserve_txt + "\"}";

                Log.d("WriteActivity", "제목 : " + edit_title + ", 내용 : " + edit_content + "" +
                        ", 현재시간 : " + getTime + "" + ", 예약날짜 : " + reserve_date[0]);

                //PreferenceManager 클래스에서 저장에 관한 메소드를 관리
                pref.setString(getApplication(), getTime, save_form);

                // Intent로 값을 MainActivity에 전달
                Intent intent = new Intent();
                intent.putExtra("date", getTime);
                intent.putExtra("title", edit_title);
                intent.putExtra("content", edit_content);
                intent.putExtra("selectedDate", reserve_txt);
                setResult(RESULT_OK, intent);

                finish();
            }
        });

        checkBox.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 체크박스가 체크되면 시간 예약하게 하기
                if (checkBox.isChecked()) {
                    // TODO : CheckBox is checked.
                    content.setVisibility(View.GONE);
                    reserve.setVisibility(View.VISIBLE);
                } else {
                    // TODO : CheckBox is unchecked.
                    content.setVisibility(View.VISIBLE);
                    reserve.setVisibility(View.GONE);
                }
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                reserve_date[0] = year + "/" + (month + 1) + "/" + dayOfMonth;
            }

        });


    }
}
