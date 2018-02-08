package carlos.coinapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Stats extends AppCompatActivity {

    private SQLiteDatabase db;
    private ListView lvToss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        /*this.getActionBar().setDisplayHomeAsUpEnabled(true);*/

        lvToss = (ListView) findViewById(R.id.lvToss);

        db = openOrCreateDatabase("coinflipdb", Context.MODE_PRIVATE, null);

        if(db==null){
            System.out.println("Db is null");
        }
        showAll();
    }


    public void showAll() {
        /*ArrayAdapter<String> adaptador;*/
        ArrayList<Toss> tossList = new ArrayList<Toss>();
        Cursor c = db.rawQuery("SELECT * FROM toss", null);

        if (c.getCount() == 0) {
            //lista.add("No hay registros");
        } else {
            while (c.moveToNext()) {
                tossList.add(new Toss(Integer.parseInt(c.getString(0)), Boolean.parseBoolean(c.getString(1)),
                        c.getString(2), c.getString(3)));
            }
            //System.out.println(c.getString(0) + " " + c.getString(1) + " - " + c.getString(2) + "   " + c.getString(3));
        }

        // Create the adapter to convert the array to views
        TossAdapter tossAdapter = new TossAdapter(this, tossList);
        // Attach the adapter to a ListView

        lvToss.setAdapter(tossAdapter);


        c.close();
    }
}
