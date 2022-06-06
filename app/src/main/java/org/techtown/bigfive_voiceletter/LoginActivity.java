package org.techtown.bigfive_voiceletter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //로그인 버튼 클릭시 아무 변화 없음
        Button login_button = (Button) findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
            }

        });

        //스킵 버튼 클릭시 메인 페이지로 넘어감
        Button go_skip_button = (Button) findViewById(R.id.go_skip_button);
        go_skip_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //레지스터 버튼 클릭시 레지스터 페이지로 넘어감
        Button go_register_button = (Button) findViewById(R.id.go_register_button);
        go_register_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}
