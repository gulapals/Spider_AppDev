package spider.proximitysensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class ProximitySensor extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    private ImageView imageView;
    private MediaPlayer mediaPlayer;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_sensor);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        imageView = (ImageView) findViewById(R.id.imageView);
        mediaPlayer = MediaPlayer.create(this, R.raw.curse);
        textView = (TextView) findViewById(R.id.textView);
    }

    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values[0] <= 1) {
            imageView.setImageResource(R.drawable.yosemitesambackoff);
            mediaPlayer.start();
//            new CountDownTimer(30000, 1000) {
//
//                public void onTick(long millisUntilFinished) {
//                    textView.setText("seconds remaining: " + millisUntilFinished / 1000);
//                }
//
//                public void onFinish() {
//                    textView.setText("done!");
//                }
//            }.start();
        }
        else{
            imageView.setImageResource(R.drawable.happy);
            mediaPlayer.pause();
        }
    }
}
