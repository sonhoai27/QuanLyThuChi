package com.sonhoai.sonho.quanlythuchi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lvThuChi;
    private List<ThuChi> thuChiList = new ArrayList<>();
    private ThuChiBaseAdapter adapter;
    private DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Quản lý thu chi");
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.addItem: actionAdd(); return true;
            case R.id.saveItem: actionSave(); return true;
            case R.id.exitItem: actionExit(); return true;
            default: return false;
        }
    }

    private void actionAdd(){
        Intent intentAdd = new Intent(MainActivity.this, AddActivity.class);
        startActivityForResult(intentAdd, 123);
    }
    private void actionSave(){
        long kq = db.add(thuChiList);
        if(kq>0){
            Toast.makeText(getApplicationContext(), "Đã thêm vào: "+kq, Toast.LENGTH_SHORT).show();
        }
    }
    private void actionExit(){
        //thoát
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 123 && data != null){
            //nhận dữ liệu từ bên màn hình 2, màn hình 1 nhận, sau đó ép về kiểu arraylist
            List<ThuChi> temp = new ArrayList<>();
            temp = (ArrayList<ThuChi>)data.getSerializableExtra("LIST");
            //duyệt từng phần tử và cập nhật lại mảng gốc
            for (int i = 0; i < temp.size(); i++){
                thuChiList.add(new ThuChi(
                   temp.get(i).getId(),
                   temp.get(i).getContent(),
                   temp.get(i).getAmount(),
                   temp.get(i).getType()
                ));
            }
            adapter.notifyDataSetChanged();
        }
    }

    private void init(){
        db = new DB(getApplicationContext(), DB.DBNAME,null, DB.VERSION);
        lvThuChi = (ListView) findViewById(R.id.lvThuChi);
        thuChiList = db.getData();

        adapter = new ThuChiBaseAdapter(
                getApplicationContext(),
                thuChiList
        );
        lvThuChi.setAdapter(adapter);
    }
}
