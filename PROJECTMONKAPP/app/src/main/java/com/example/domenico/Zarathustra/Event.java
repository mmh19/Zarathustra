package com.example.domenico.Zarathustra;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Calendar;

public class Event extends AppCompatActivity implements View.OnClickListener {
    ImageButton addTime;
    ImageButton addDate;
    EditText content, titolo;
    TextView date_time_text;
    ImageButton addToDb;
    public Context c;
    private DatePickerDialog.OnDateSetListener mDataSetListener;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        addTime=findViewById(R.id.addTime);
        content= findViewById(R.id.content);
        titolo=findViewById(R.id.titolo);
        date_time_text=findViewById(R.id.date_time_text);
        date_time_text.setOnClickListener(this);
        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        c,
                        android.R.style.Theme_Material_Light_Dialog,
                        mDataSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();

                mDataSetListener = new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day){
                        data = "" + day + "/" + month + "/" + year;
                        date_time_text.setText(data);
                    }
                };
        addToDb= addToDb.findViewById(R.id.addEvent);
        addToDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEvent = new Intent(Event.this,Message_Wall.class);
                intentEvent.putExtra("Data",data);
                intentEvent.putExtra("Titolo", titolo.getText().toString());
                intentEvent.putExtra("Contenuto", content.getText().toString());
                startActivity(intentEvent);
            }
        });
            }
        });

     }
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.addTime:
                DialogFragment timepicker = new TimePicker();
                timepicker.show(getSupportFragmentManager(),"Time Picker");
                break;
        }
    }


    public Context getContext(){
        return this.c;
    }
}
