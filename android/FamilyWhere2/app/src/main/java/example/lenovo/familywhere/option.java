package example.lenovo.familywhere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class option extends AppCompatActivity {
    private Button findButton;
    private Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        findButton = findViewById(R.id.findButton);
        addButton = findViewById(R.id.addButton);
        final Intent intent = new Intent(getApplicationContext(),takePhoto.class);

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("type","find");
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("type","add");
                startActivity(intent);
            }
        });
    }

    public static class form_data_upload {
    }
}
