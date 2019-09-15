package com.mrphd.e_uitm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText inputUser, inputPass;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        try { this.getSupportActionBar().hide(); } catch (final NullPointerException ignored){}

        inputUser = findViewById(R.id.InputUser);
        inputPass = findViewById(R.id.InputPass);
        buttonLogin = findViewById(R.id.ButtonLogin);

        buttonLogin.setOnClickListener(event -> {
            HttpRequest request = new HttpRequest.Post()
                    .withHeader("Connection", "Keep-Alive")
                    .withParam("login", inputUser.getText().toString())
                    .withParam("password", inputPass.getText().toString());
            try
            {
                final String responseText = request.execute("https://simsweb.uitm.edu.my/SPORTAL_APP/SPORTAL_LOGIN/index.cfm").get();
                if(responseText.contains("Successfully Login")){
                    Toast.makeText(LoginActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                    final Intent intent = new Intent(new Intent(LoginActivity.this, MainActivity.class));
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }catch (final Exception e){
                Toast.makeText(LoginActivity.this, "Error occur! Please check your internet connection and try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
