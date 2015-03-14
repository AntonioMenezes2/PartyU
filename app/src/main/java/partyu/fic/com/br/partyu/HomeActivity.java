package partyu.fic.com.br.partyu;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class HomeActivity extends Activity {

    static final int DATE_DIALOG_ID = 0;
    static final int Time_DIALOG_ID = 1;
    private Button pPickDate;
    private int pYear;
    private int pMonth;
    private int pDay;
    private TextView pDisplayDate;
    private Button ClickHoras;
    private int horas;
    private int minutos;
    private TextView tempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pDisplayDate = (TextView) findViewById(R.id.textView2);
        pPickDate = (Button) findViewById(R.id.button1);
        ClickHoras = (Button) findViewById(R.id.buttonHoras);
        tempo = (TextView) findViewById(R.id.textViewHoras);

        /** Listener for click event of the button */
        pPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        ClickHoras.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View arg0) {
                showDialog(Time_DIALOG_ID);

            }
        });

        /** Get the current date */
        final Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
        pMonth = cal.get(Calendar.MONTH);
        pDay = cal.get(Calendar.DAY_OF_MONTH);
        horas = cal.get(Calendar.HOUR);
        minutos = cal.get(Calendar.MINUTE);
        /** Display the current date in the TextView */
        updateDisplay();

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.backgroud_action_bar));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);

        MenuItem m1 = menu.add(0,0,0,"Item 1");
        m1.setIcon(R.drawable.sucesso);
        m1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    private DatePickerDialog.OnDateSetListener pDateSetListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            pYear = year;
            pMonth = monthOfYear;
            pDay = dayOfMonth;
            updateDisplay();
            displayToast();
        }
    };

    private TimePickerDialog.OnTimeSetListener pTimeSetListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            horas = hourOfDay;
            minutos = minute;
            updateDisplayTime();
//			displayToastTime();
        }



        private void updateDisplayTime() {
            tempo.setText(new StringBuilder().append(" ").append(horas).append(" : ").append(minutos).append(" "));

        }
    };

    /** Updates the date in the TextView */
    private void updateDisplay() {
        pDisplayDate.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(pDay).append("/").append(pMonth + 1).append("/")
                .append(pYear).append(" "));
    }

    /** Displays a notification when the date is updated */
    private void displayToast() {
        Toast.makeText(
                this,
                new StringBuilder().append("Date choosen is ").append(
                        pDisplayDate.getText()), Toast.LENGTH_SHORT).show();

    }

    /** Create a new dialog for date picker */
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, pDateSetListener, pYear, pMonth,
                        pDay);


            case Time_DIALOG_ID:
                return new TimePickerDialog(this, pTimeSetListener, horas, minutos, false);
        }
        return null;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()){
            case 0:
                Toast.makeText(this,"Tela cadastro",Toast.LENGTH_LONG).show();
                break;
        }
        return super.onMenuItemSelected(featureId, item);
    }
}
