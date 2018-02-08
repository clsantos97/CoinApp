package carlos.coinapp;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Carlos on 08/02/2018.
 */

public class Toss {

    private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    private int id;
    private boolean res;
    private String resmsg;
    private String date;

    public Toss(int id, boolean res, String resmsg) {
        this.setId(id);
        this.setRes(res);
        this.setResmsg(resmsg);
        this.setDate(formatter.format(new Date()));
    }

    public Toss(int id, boolean res, String resmsg, String date) {
        this.setId(id);
        this.setRes(res);
        this.setResmsg(resmsg);
        this.setDate(date);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRes() {
        return res;
    }

    public void setRes(boolean res) {
        this.res = res;
    }

    public String getResmsg() {
        return resmsg;
    }

    public void setResmsg(String resmsg) {
        this.resmsg = resmsg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
