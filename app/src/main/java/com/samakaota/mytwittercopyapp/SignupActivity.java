package com.samakaota.mytwittercopyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignupActivity extends AppCompatActivity {

    private Button btnSave;
    private EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        edtName = findViewById(R.id.edtName);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtKickPower = findViewById(R.id.edtKickPower);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              try {
                  ParseObject kickBoxer = new ParseObject("KickBoxer");
                  kickBoxer.put("name", edtName.getText().toString());
                  kickBoxer.put("punchSpeed", Integer.parseInt(edtPunchSpeed.getText().toString()));
                  kickBoxer.put("punchPower", Integer.parseInt(edtPunchPower.getText().toString()));
                  kickBoxer.put("kickSpeed", Integer.parseInt(edtKickSpeed.getText().toString()));
                  kickBoxer.put("kickPower", Integer.parseInt(edtKickPower.getText().toString()));
                  kickBoxer.saveInBackground(new SaveCallback() {
                      @Override
                      public void done(ParseException e) {
                          if (e == null) {
                              FancyToast.makeText(SignupActivity.this,
                                      kickBoxer.get("name") + " is saved successfully",
                                      FancyToast.LENGTH_LONG,
                                      FancyToast.SUCCESS,
                                      true).show();

                          } else {
                              FancyToast.makeText(SignupActivity.this,
                                      e.getMessage()+"Error!!!",
                                      FancyToast.LENGTH_LONG,
                                      FancyToast.ERROR,
                                      true).show();
                          }
                      }
                  });
              }catch (Exception e){
                  FancyToast.makeText(SignupActivity.this,
                          e.getMessage()+"Error!!!",
                          FancyToast.LENGTH_LONG,
                          FancyToast.ERROR,
                          true).show();
              }
            }
        });
    }
}