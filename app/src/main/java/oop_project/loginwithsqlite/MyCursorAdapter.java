package oop_project.loginwithsqlite;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Sumon on 7/29/2017.
 */

public class MyCursorAdapter extends CursorAdapter {

    private LayoutInflater inflater;

    public MyCursorAdapter(Context context, Cursor c) {
        super(context, c);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.list_item_cursor, parent, false);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView titleTextView = (TextView) view.findViewById(R.id.list_title);

        TextView detailsTextView = (TextView) view.findViewById(R.id.list_details);

        String titleString = null;
        String detailsString = null;
        try {
            titleString = cursor.getString(1);
            detailsString = cursor.getString(2);
        } catch (Exception e) {
            Log.d("TAG", "Exception on: " + e.toString());
        }

        titleTextView.setText(titleString);
        detailsTextView.setText(detailsString);


    }
}
