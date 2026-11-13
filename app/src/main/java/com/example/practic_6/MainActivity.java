package com.example.practic_6;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final static String FILE_NAME = "document.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private File getExternalPath() {
        return new File(getExternalFilesDir(null), FILE_NAME);
    }

    public void saveText(View view) {
        try (FileOutputStream fos = new FileOutputStream(getExternalPath())) {
            EditText editText = findViewById(R.id.editor);
            String text = editText.getText().toString();
            fos.write(text.getBytes());
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void openText(View view) {
        TextView textView = findViewById(R.id.text);
        File file = getExternalPath();

        if (!file.exists()) return;

        try (FileInputStream fin = new FileInputStream(file)) {
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            textView.setText(text);
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}