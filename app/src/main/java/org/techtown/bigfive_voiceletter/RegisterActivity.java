package org.techtown.bigfive_voiceletter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //레지스터 버튼 클릭시 아무 변화 없음
        /*
        Button register_btn = (Button) findViewById(R.id.register_btn);
        register_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
            }

        });
         */

        //고로그인 버튼 누르면 다시 로그인 화면으로 넘어감
        Button go_login_button = (Button) findViewById(R.id.go_login_button);
        go_login_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), org.techtown.bigfive_voiceletter.LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
