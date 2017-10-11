package com.elano.dreamlisterapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;

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
        Bitmap bm = ((BitmapDrawable) mIvPicture.getDrawable()).getBitmap();
        Item item = new Item(getByteArray(bm), mEtName.getText().toString(), mEtDescription.getText().toString(),
                Double.parseDouble(mEtPrice.getText().toString()));
        Intent intent = getIntent();
        intent.putExtra(KEY_ADD, item);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private byte[] getByteArray(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}
