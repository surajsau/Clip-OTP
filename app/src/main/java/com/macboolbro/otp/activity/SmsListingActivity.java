package com.macboolbro.otp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.macboolbro.otp.R;
import com.macboolbro.otp.adapter.OTPRecyclerAdapter;
import com.macboolbro.otp.db.OTPDataSource;
import com.macboolbro.otp.db.OTPModel;
import com.macboolbro.otp.listener.OnItemDeleteListener;

import java.util.ArrayList;

public class SmsListingActivity extends AppCompatActivity implements OnItemDeleteListener {

    private static final String TAG = SmsListingActivity.class.getSimpleName();

    private OTPDataSource dataSource;
    private ArrayList<OTPModel> models;

    private RecyclerView rlOtpSmsList;
    private LinearLayoutManager llmOtpList;
    private OTPRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_listing);

        setupDataSource();

        initResources();
        setupToolbar();

        instantTiateSwipe();
    }

    private void instantTiateSwipe() {
        ItemTouchHelper.SimpleCallback simpleSwipeCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {return false;}

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                OTPRecyclerAdapter.OTPViewHolder holder = (OTPRecyclerAdapter.OTPViewHolder) viewHolder;
                holder.deleteModel();

                Toast.makeText(getActivity(), "Removed", Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper swipeHelper = new ItemTouchHelper(simpleSwipeCallback);
        swipeHelper.attachToRecyclerView(rlOtpSmsList);
    }

    private void initResources() {
        rlOtpSmsList = (RecyclerView) findViewById(R.id.rlSmsList);

        llmOtpList = new LinearLayoutManager(this);
        adapter = new OTPRecyclerAdapter(this, models);

        rlOtpSmsList.setLayoutManager(llmOtpList);
        rlOtpSmsList.setAdapter(adapter);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupDataSource() {
        dataSource = new OTPDataSource(getApplicationContext());
        dataSource.open();

        models = new ArrayList<>();

        if(dataSource.getNumberOfRows() > 0)
            models.addAll(dataSource.getAllOTPModels());

        Log.d(TAG, "open dataSource");
    }

    @Override
    public void onItemDelete(OTPModel model) {
        dataSource.deleteOTPModel(model);
        models.remove(model);
    }

    public Context getActivity() {
        return SmsListingActivity.this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sms_listing, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.menu_delete: {
                deleteAllSms();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteAllSms() {
        models.clear();
        dataSource.deleteAll();
        adapter.clearData();
    }
}
