package com.example.joan.gg;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class Camera extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Intent intent_camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent_camera, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //拍照後顯示圖片
        ImageView iv = (ImageView)findViewById(R.id.imagecaptured);
        if (resultCode == RESULT_OK)
        {
            //取出拍照後回傳資料
            Bundle extras = data.getExtras();
            //將資料轉換為圖像格式
            Bitmap bmp = (Bitmap) extras.get("data");
            //載入ImageView
            iv.setImageBitmap(bmp);
        }

        //覆蓋原來的Activity
       // super.onActivityResult(requestCode, resultCode, data);
    }
}
