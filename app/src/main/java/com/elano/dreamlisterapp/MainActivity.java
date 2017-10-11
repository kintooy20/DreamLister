package com.elano.dreamlisterapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_ADD = "key-add";
    private static final int REQUEST_CODE_ADD = 1;
    private Item item;
    private List<Item> mLists;
    private RecyclerView mRecyclerView;
    private DatabaseHandler db = new DatabaseHandler(this);
    private WishListAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mLists = db.getAllItems();
        adapter = new WishListAdapter(mLists);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void addWishList(View view) {
        item = new Item();
        Intent intent = new Intent(this, AddWishListItemActivity.class);
        intent.putExtra(KEY_ADD, item);
        startActivityForResult(intent, REQUEST_CODE_ADD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK && data != null) {
            item = (Item) data.getSerializableExtra(AddWishListItemActivity.KEY_ADD);
            db.addWishListItem(item);
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }
}