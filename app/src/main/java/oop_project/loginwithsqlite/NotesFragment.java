package oop_project.loginwithsqlite;


import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotesFragment extends Fragment implements View.OnClickListener {

    public static String TAG = "TAG";

    DatabaserHelper dbhelper;
    FloatingActionButton floatingActionButton;
    MyCursorAdapter cursorAdapter;
    Intent intent;
    Intent intentForAddTask;
    String userName;
    Cursor cursor;
    ListView notesListView;

    public NotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        notesListView = (ListView) view.findViewById(R.id.notes_list_view);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton_notes);
        dbhelper = new DatabaserHelper(getActivity());

        SharedPreferences preferences = getActivity().getSharedPreferences("FOR_ALL_FRAGMENTS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();


        floatingActionButton.setOnClickListener(this);

        Intent mainIntent = getActivity().getIntent();
        final String userName = mainIntent.getStringExtra("LOGGED_IN_USER");

        intentForAddTask = new Intent(getActivity(), AddTaskActivity.class);


        if (userName != null) {
            cursor = dbhelper.getCursorForTask(userName);
            cursorAdapter = new MyCursorAdapter(getActivity(), cursor);
            notesListView.setAdapter(cursorAdapter);

            Log.d("TAG", "userName is not null saving data: " + userName);
            editor.putString("SHARED_USER", userName);
            editor.commit();
            intentForAddTask.putExtra("USERNAME_", userName);


            notesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                    Log.d(TAG, "onItemLongClick: " + position + " id: 0" + id);

                    final List<String> taskList = dbhelper.getTaskList(userName);

                    Log.d(TAG, "onItemLongClick: " + taskList);

                    // dbhelper.deleteTask(userName, taskList.get(position));


                    //   taskList.remove(position);


                    // cursorAdapter.notifyDataSetChanged();


                    //    ListAdapter adapter = notesListView.getAdapter();

                    //    adapter.notifyDataSetChanged();

                    cursor = dbhelper.getCursorForTask(userName);


                    AlertDialog.Builder itemLongAlart = new AlertDialog.Builder(getActivity())
                            .setCancelable(true)
                            .setMessage(taskList.get(position));

                    final int pos = position;

                    itemLongAlart.setTitle("Options");
                    itemLongAlart.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            dbhelper.deleteTask(userName, taskList.get(pos));

                            taskList.remove(pos);


                            cursor = dbhelper.getCursorForTask(userName);

                            MyCursorAdapter newAdapter = new MyCursorAdapter(getActivity(), cursor);

                            notesListView.setAdapter(newAdapter);

                            newAdapter.notifyDataSetChanged();

                        }
                    });

                    itemLongAlart.show();


                    MyCursorAdapter newAdapter = new MyCursorAdapter(getActivity(), cursor);

                    notesListView.setAdapter(newAdapter);

                    newAdapter.notifyDataSetChanged();


                    return true;
                }
            });


        } else {
            Log.d("TAG", "userName is null, maybe called from add task activity: getting data from shared prefs!");
            final String shared_usedString = preferences.getString("SHARED_USER", "defaultValue");
            Log.d("TAG", "got from shared prefs: " + shared_usedString);

            cursor = dbhelper.getCursorForTask(shared_usedString);
            intentForAddTask.putExtra("USERNAME_", shared_usedString);

            cursor = dbhelper.getCursorForTask(shared_usedString);
            cursorAdapter = new MyCursorAdapter(getActivity(), cursor);
            notesListView.setAdapter(cursorAdapter);


            notesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    cursorAdapter.notifyDataSetChanged();

                    notesListView.setAdapter(cursorAdapter);

                    Log.d(TAG, "onItemLongClick: " + position + " id: 0" + id);


                    //   dbhelper.getTask(userName);

                    final List<String> taskList = dbhelper.getTaskList(shared_usedString);

                    //  dbhelper.deleteTask(userName, "task");

                    Log.d(TAG, "onItemLongClick: " + taskList);


                    cursor = dbhelper.getCursorForTask(shared_usedString);


                    // cursorAdapter.notifyDataSetChanged();


                    AlertDialog.Builder itemLongAlart = new AlertDialog.Builder(getActivity())
                            .setCancelable(true)
                            .setMessage(taskList.get(position));


                    itemLongAlart.setTitle("Options");

                    final int pos = position;


                    itemLongAlart.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            dbhelper.deleteTask(shared_usedString, taskList.get(pos));

                            taskList.remove(pos);


                            cursor = dbhelper.getCursorForTask(shared_usedString);

                            MyCursorAdapter newAdapter = new MyCursorAdapter(getActivity(), cursor);

                            notesListView.setAdapter(newAdapter);

                            newAdapter.notifyDataSetChanged();

                        }
                    });

                    itemLongAlart.show();


                    return true;
                }
            });


        }


        intent = new Intent(getActivity(), AddTaskActivity.class);


        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG", "Clicked on: " + position);
                //  Toast.makeText(getContext(), "Item: " + position + " will notify at: 2:13 pm", Toast.LENGTH_SHORT).show();
                //  notifyMe(2, 13, "Item Task Demo");

                CustomDialog cdd = new CustomDialog(getActivity());
                cdd.show();

            }
        });


        return view;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.floatingActionButton_notes) {
            startActivity(intentForAddTask);
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        // Log.d("TAG", "onResume called frag_notes");

        try {
            cursorAdapter.notifyDataSetChanged();
            Log.d("TAG", "notified cursorAdapter from onResume()");
        } catch (Exception e) {
            Log.d("TAG", e.toString());
        }

    }


    public void notifyMe(int hour, int min, String taskName) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MINUTE, min);
        cal.set(Calendar.HOUR, hour);

        AlarmManager am;
        PendingIntent pi;

        Intent intent = new Intent(getActivity(), TaskNotificationReceiver.class);
        intent.putExtra("task_name", taskName);


        pi = PendingIntent.getBroadcast(getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        am = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

        am.set(0, cal.getTimeInMillis(), pi);


    }


}
