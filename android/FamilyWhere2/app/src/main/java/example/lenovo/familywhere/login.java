package example.lenovo.familywhere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileWriter;

public class login extends AppCompatActivity {
    private Button loginButton;
    private EditText email;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validated_user(email.getText().toString().trim(),password.getText().toString().trim())){
                    Intent intent = new Intent(getApplicationContext(),option.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private boolean validated_user(String a, String b) {
        /*
        *
        * Here I pass these parameters to check if the username and password for the given account is correct or not
        * if correct, save it in mobile database
        * else gaand marao
        *
        */
        return (a.equals("ayush") && b.equals("123"));
    }
}
