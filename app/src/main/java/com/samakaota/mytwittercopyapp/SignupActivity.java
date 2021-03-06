package com.samakaota.mytwittercopyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignupActivity extends AppCompatActivity {

    private Button btnSave, btnGetAllData, btnTransition;
    private EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;
    private TextView txtGetData, txtShowAllKickBoxers;

    private String allKickBoxers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        edtName = findViewById(R.id.edtName);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtKickPower = findViewById(R.id.edtKickPower);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        txtGetData = findViewById(R.id.txtGetData);
        btnGetAllData = findViewById(R.id.btnGetAllKickBoxer);
        txtShowAllKickBoxers = findViewById(R.id.txtShowAllKickBoxers);

        allKickBoxers = "";

        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
                queryAll.whereGreaterThan("punchPower",3000);
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null){
                            if (objects.size() > 0){
                                for (ParseObject kickBoxer : objects) {
                                    allKickBoxers = allKickBoxers + kickBoxer.get("name") + "\n";
                                }
                               /* FancyToast.makeText(SignupActivity.this,
                                        "successfully so!!!!",
                                        FancyToast.LENGTH_LONG,
                                        FancyToast.SUCCESS,
                                        true).show();*/
                                txtShowAllKickBoxers.setText(allKickBoxers);


                            }
                            else {

                            }
                        }
                    }
                });
            }
        });

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("Nd9VnPVVnc", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object != null && e == null){
                            txtGetData.setText(object.get("name")+ "-" +"Punch Power: " + object.get("punchPower"));
                        }else {

                            FancyToast.makeText(SignupActivity.this,
                                    e.getMessage()+"Error!!!",
                                    FancyToast.LENGTH_LONG,
                                    FancyToast.ERROR,
                                    true).show();
                        }
                    }
                });
            }
        });

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

        btnTransition = findViewById(R.id.btnNextActivity);
        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}