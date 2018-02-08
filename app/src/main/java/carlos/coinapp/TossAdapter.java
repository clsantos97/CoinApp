package carlos.coinapp;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Carlos on 08/02/2018.
 */

public class TossAdapter extends ArrayAdapter<Toss> {


    public TossAdapter(Context context, ArrayList<Toss> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Toss tossItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.toss_listview, parent, false);
        }

        // Lookup view for data population
        TextView tvRes = (TextView) convertView.findViewById(R.id.tvRes);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
        TextView tvResmsg = (TextView) convertView.findViewById(R.id.tvResmsg);

        // Populate the data into the template view using the data object
        String res = tossItem.isRes() ? "CARA" : "CRUZ";

        tvRes.setText(res);
        tvDate.setText(tossItem.getDate());
        tvResmsg.setText(tossItem.getResmsg());

        // Return the completed view to render on screen

        // Styling font
/*        Typeface subtitleTypeFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/JosefinSans-Bold.ttf");
        Typeface detailTypeFace =
                Typeface.createFromAsset(getContext().getAssets(), "fonts/JosefinSans-SemiBoldItalic.ttf");
        Typeface titleTypeFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/Quicksand-Bold.otf");*/

/*        tvTask.setTypeface(titleTypeFace);
        tvDate.setTypeface(detailTypeFace);
        tvTime.setTypeface(subtitleTypeFace);
        tvDesc.setTypeface(detailTypeFace);*/


        return convertView;
    }
}
