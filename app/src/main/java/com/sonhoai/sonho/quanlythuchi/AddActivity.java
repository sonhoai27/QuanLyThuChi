package com.sonhoai.sonho.quanlythuchi;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AddActivity extends AppCompatActivity {
    private EditText edtContent, edtAmount;
    private RadioGroup radioThuChi;
    private RadioButton radioLoai;
    private Button btnAdd, btnReType, btnShow;
    private ArrayList<ThuChi> thuChiList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setTitle("Thêm mới");
        init();
        add();
        retype();
        show();
    }

    private void init(){
        edtContent = (EditText) findViewById(R.id.edtContent);
        edtAmount = (EditText) findViewById(R.id.edtAmount);
        radioThuChi = (RadioGroup) findViewById(R.id.radioHinhThuc);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnReType = (Button) findViewById(R.id.btnRetype);
        btnShow = (Button) findViewById(R.id.btnShow);
    }

    public void add(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioThuChi.getCheckedRadioButtonId();
                radioLoai = (RadioButton)findViewById(selectedId);
                thuChiList.add(new ThuChi(
                        randomNumber(),
                        edtContent.getText().toString(),
                        Integer.parseInt(edtAmount.getText().toString()),
                        Integer.parseInt(radioLoai.getTag().toString())
                ));
                edtAmount.setText("");
                edtContent.setText("");
            }
        });
    }
    public void retype(){
        btnReType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtAmount.setText("");
                edtContent.setText("");
                edtContent.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(edtContent, InputMethodManager.SHOW_IMPLICIT);
            }
        });
    }
    public void show(){
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                intent.putExtra("LIST", thuChiList);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private int randomNumber(){
        Random random = new Random();
        String num = "123456789098765432156789234565843742983726423826264";
        int leng = num.length();
        String ran = "";
        for(int i = 0; i < 4;i++){
            ran += num.charAt(random.nextInt(leng - 1));
        }
        return Integer.parseInt(ran);
    }
}
