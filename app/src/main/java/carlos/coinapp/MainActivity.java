package carlos.coinapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener{

    ImageView ivLogo;
    Button btnFlip;
    Button btnStats;
    Button btnSource;
    Animation shake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get view elements
        ivLogo = (ImageView)findViewById(R.id.ivLogo);
        btnFlip = (Button)findViewById(R.id.btnFlip);
        btnStats = (Button)findViewById(R.id.btnStats);
        btnSource = (Button)findViewById(R.id.btnSource);

        // Add OnClickListener
        ivLogo.setOnClickListener(this);
        btnFlip.setOnClickListener(this);
        btnStats.setOnClickListener(this);
        btnSource.setOnClickListener(this);

        // Get animations
        shake = AnimationUtils.loadAnimation(this, R.anim.shake);
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
            ivLogo.startAnimation(shake);
    }

    @Override
    public void onClick(View view) {
        if(view == ivLogo){
            ivLogo.startAnimation(shake);
        }else if(view == btnFlip){
            startActivity(new Intent(MainActivity.this, CoinFlipper.class));
        }else if(view == btnStats){
            startActivity(new Intent(MainActivity.this, Stats.class));
        }else if(view == btnSource){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/clsantos97/CoinApp"));
            startActivity(browserIntent);
        }
    }

}
