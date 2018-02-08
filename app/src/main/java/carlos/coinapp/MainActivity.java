package carlos.coinapp;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener{

    private ImageView ivLogo;
    private Button btnFlip;
    private Button btnStats;
    private Button btnSource;
    private Animation shake;
    private SQLiteDatabase db;
    private static final Logger logger = Logger.getLogger(MainActivity.class.getName());
    private static ArrayList<Toss> mockData = new ArrayList<>();

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

        // db
        initDb();
        initMockData();
        insertMockData();
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

    public void initDb() {

        // Init DB
        db = openOrCreateDatabase("coinflipdb", Context.MODE_PRIVATE, null);

        //db.execSQL("DROP TABLE toss");
        db.execSQL("CREATE TABLE IF NOT EXISTS toss(" +
                "id INTEGER PRIMARY KEY,res BOOLEAN, resmsg VARCHAR, date VARCHAR);");
        db.execSQL("DELETE FROM toss");

        //if (!isDbItemsEmpty()) {

        //}
    }

    public void insertMockData() {
        try {
            String query;
            for (Toss mockToss:mockData) {
                query = "INSERT INTO toss (id, res, resmsg, date) " +
                        "VALUES ('"+mockToss.getId()+"','"+mockToss.isRes()+"','"+mockToss.getResmsg()+"','"+mockToss.getDate()+"')";
                try {
                    db.execSQL(query);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.info("Error al insertar mockdata.");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Error al añadir datos de prueba");
        }
    }

    public void initMockData(){
        mockData.add(new Toss(1,true,"hacer a"));
        mockData.add(new Toss(2,false,"hacer b"));
        mockData.add(new Toss(3,false,"Devolver El ultimo mohicano a biblioteca"));
        mockData.add(new Toss(4,true,"Dormir"));
    }



}
