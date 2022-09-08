package co.edu.unipiloto.lab_ciclodevida;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds=0;
    private int lap=0;
    private boolean running;
    private int lapTime = 0;
    private TextView lapView1;
    private TextView lapView2;
    private TextView lapView3;
    private TextView lapView4;
    private TextView lapView5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running =savedInstanceState.getBoolean("runnig");
            lap =savedInstanceState.getInt("lap");
        }
        runTimer();
    }

    public void onSavedInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("runnig",running);
        savedInstanceState.putInt("lap",lap);

    }

    public void onClickLap(View view) {
        lap++;
        lapTime += seconds;
        //seconds = 0;

        int lapHours = lapTime /3600;
        int lapMinutes = (lapTime % 3600 )/60;
        int lapSeconds = lapTime % 60;
        String total = String.format(Locale.getDefault(), "%d:%02d:%02d",lapHours,lapMinutes,lapSeconds);
        if (lap ==1) {
            lapView1.setText("lap " + lap + " :" + total +"\n");
        }
        else if (lap ==2) {
            lapView2.setText("lap " + lap + " :" + total +"\n");
        }
        else if (lap ==3) {
            lapView3.setText("lap " + lap + " :" + total +"\n");
        }
        else if (lap ==4) {
            lapView4.setText("lap " + lap + " :" + total +"\n");
        }
        else if (lap ==5) {
            lapView5.setText("lap " + lap + " :" + total +"\n");
        }

        lapTime = 0;
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickRest(View view) {
        running = false;
        seconds = 0;
        lapTime = 0;
        lap = 0;
        lapView1.setText("");
        lapView2.setText("");
        lapView3.setText("");
        lapView4.setText("");
        lapView5.setText("");
    }

    public void onClickStart(View view) {
        running = true;
    }

    private void runTimer(){
        final TextView  timeView = findViewById(R.id.time_view);
        lapView1 = findViewById(R.id.lap1_view);
        lapView2 = findViewById(R.id.lap2_view);
        lapView3 = findViewById(R.id.lap3_view);
        lapView4 = findViewById(R.id.lap4_view);
        lapView5 = findViewById(R.id.lap5_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600)/60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this, 100);
            }
        });
    }


}