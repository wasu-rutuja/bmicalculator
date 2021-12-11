package com.example.bmicalculator;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    android.widget.Button mcalculatebmi,mviewRecord;
    TextView mcurrentheight;
    TextView mcurrentage, mcurrentweight;
    ImageView mincrementage,mincrementweight,mdecrementage,mdecrementweight;
    SeekBar mseekbarforheight;
    RelativeLayout mmale,mfemale;

    DBHelper dbh=new DBHelper(this);

    int intweight=55;
    int intage=22;
    int currentprogress;
    String mintprogress="170";
    String typeofuser="0";
    String weight2="55";
    String age2="22";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        mcalculatebmi=findViewById(R.id.calculatebmi);
        //-- assign xml id to java id-->
        mcurrentage=findViewById(R.id.currentage);
        mcurrentweight=findViewById(R.id.currentweight);
        mcurrentheight=findViewById(R.id.currentheight);
        mincrementage=findViewById(R.id.incrementage);
        mincrementweight=findViewById(R.id.incrementweight);
        mdecrementage=findViewById(R.id.decrementage);
        mdecrementweight=findViewById(R.id.decrementweight);
        mseekbarforheight=findViewById(R.id.seekbarforheight);
        mmale=findViewById(R.id.male);
        mfemale=findViewById(R.id.female);
        mviewRecord=findViewById(R.id.viewRecord);


        mviewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = dbh.view();
                if (cursor.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                }
                StringBuffer str = new StringBuffer();
                while (cursor.moveToNext()) {
                    str.append("Date : " + cursor.getString(0) + "\n");
                    str.append("Age : " + cursor.getString(1) + "\n");
                    str.append("Height : " + cursor.getString(2) + "m"+"\n");
                    str.append("Weight : " + cursor.getString(3) + "\n");
                    str.append("BMI : " + cursor.getString(4) + "\n");
                    str.append(" "+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Past BMI Record");
                builder.setMessage(str.toString());
                builder.show();
            }
        });
        mmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                typeofuser="Male";
            }
        });

        mfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                typeofuser="Female";
            }
        });

        mseekbarforheight.setMax(300);
        mseekbarforheight.setProgress(170);
        mseekbarforheight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentprogress=progress;
                mintprogress=String.valueOf(currentprogress);
                mcurrentheight.setText(mintprogress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        mincrementage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intage=intage+1;
                age2=String.valueOf(intage);
                mcurrentage.setText(age2);
            }
        });
        mdecrementage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intage=intage-1;
                age2=String.valueOf(intage);
                mcurrentage.setText(age2);
            }
        });
        mincrementweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intweight=intweight+1;
                weight2=String.valueOf(intweight);
                mcurrentweight.setText(weight2);
            }
        });
        mdecrementweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intweight=intweight-1;
                weight2=String.valueOf(intweight);
                mcurrentweight.setText(weight2);
            }
        });



        mcalculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(typeofuser.equals("0")){
                    Toast.makeText(getApplicationContext(),"Select Gender",Toast.LENGTH_SHORT).show();
//                    View view1 = toast.getView();
//
//                    view1.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
//                    TextView text = view.findViewById(android.R.id.message);
//                    text.setTextColor(Color.BLACK);
//                    toast.show();
                    Intent intent =new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else if(mintprogress.equals("0")){
                    Toast.makeText(getApplicationContext(),"Select Height",Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else if(intage==0|| intage<0){
                    Toast.makeText(getApplicationContext(),"Incorrect Age",Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else if(intweight<=0){
                    Toast.makeText(getApplicationContext(),"Incorrect Weight",Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);

                }
                else{
                    double height1=Integer.parseInt(mintprogress)/100.0;
                    int weight1=Integer.parseInt(weight2);
                    int age1=Integer.parseInt(age2);
                    double bmi=weight1/(height1*height1);
                    //String n = age2;
                    //String m = weight2.getText().toString();
                    //String r = r.getText().toString();

                    boolean check = dbh.insert(age1, weight1,height1, bmi);
                    if (check == true) {
                        Toast.makeText(MainActivity.this, "Insertion Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Insertion Failed", Toast.LENGTH_SHORT).show();
                    }


                    Intent intent =new Intent(MainActivity.this,bmiactivity.class);
                    intent.putExtra("gender",typeofuser);
                    intent.putExtra("height",mintprogress);
                    intent.putExtra("age",age2);
                    intent.putExtra("weight",weight2);
                    startActivity(intent);

                }

                finish();
            }
        });
    }
}