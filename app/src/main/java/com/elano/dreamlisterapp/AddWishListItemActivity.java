package com.elano.dreamlisterapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class AddWishListItemActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CAMERA = 1;
    public static final String KEY_ADD = "key-add";
    private ImageView mIvPicture;
    private EditText mEtName, mEtDescription, mEtPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_wish_list_item);
        findViews();
    }

    private void findViews() {
        mIvPicture = (ImageView) findViewById(R.id.ivItem);
        mEtName = (EditText) findViewById(R.id.etName);
        mEtDescription = (EditText) findViewById(R.id.etDescription);
        mEtPrice = (EditText) findViewById(R.id.etPrice);
    }

    public void addPhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            Bitmap bm = (Bitmap) extras.get("data");
            mIvPicture.setImageBitmap(bm);
        }
    }

    public void addToWishList(View view) {
        try {
            Bitmap bm = ((BitmapDrawable) mIvPicture.getDrawable()).getBitmap();
            String name = mEtName.getText().toString(), description = mEtDescription.getText().toString();
            double price = Double.parseDouble(mEtPrice.getText().toString());
            Item item = new Item(getByteArray(bm), name, description, price);
            Intent intent = getIntent();
            intent.putExtra(KEY_ADD, item);
            setResult(Activity.RESULT_OK, intent);
            finish();
        } catch (Exception e) {
            Toast.makeText(this, "Please provide empty field(s).", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private byte[] getByteArray(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}
