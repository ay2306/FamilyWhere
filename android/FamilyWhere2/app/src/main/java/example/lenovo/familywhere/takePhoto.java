package example.lenovo.familywhere;

import android.app.Notification;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class takePhoto extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private ImageView imageView;
    private Button uploadBtn;
    Bitmap  mBitmap;
    private Uri imgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        Bundle data = getIntent().getExtras();
        Button sendDataButton = findViewById(R.id.sendEntity);
        sendDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                form_data_uploading fdu = new form_data_uploading();
                fdu.doInBackground(imgUri);
            }
        });
        if(data.getString("type").trim().equals("add")){
            EditText a = findViewById(R.id.entity_first_name);
            EditText b = findViewById(R.id.entity_last_name);
            a.setVisibility(View.VISIBLE);
            b.setVisibility(View.VISIBLE);
        }
        if(data.getString("type").trim().equals("find")){
            EditText a = findViewById(R.id.entity_first_name);
            EditText b = findViewById(R.id.entity_last_name);
            a.setVisibility(View.GONE);
            b.setVisibility(View.GONE);
        }

        imageView = findViewById(R.id.imgView);
        uploadBtn = findViewById(R.id.uploadButton);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent,REQUEST_CODE);
//            }
//        });
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "select image"), REQUEST_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && data.getData() != null && requestCode == REQUEST_CODE && resultCode == RESULT_OK)
            imgUri = data.getData();

        try {
            if(imgUri!=null) {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), imgUri);
                imageView.setImageBitmap(bm);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public String getImageExt(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }

}

