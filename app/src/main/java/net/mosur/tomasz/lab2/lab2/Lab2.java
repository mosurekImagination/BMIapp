package net.mosur.tomasz.lab2.lab2;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Lab2 extends AppCompatActivity implements View.OnClickListener{

    private static final String PREFERENCES_NAME = "myPreferences";
    private Button count_bmi_button;
    EditText weight_input;
    EditText height_input;
    TextView result_label;
    TextView result_msg;
    Switch kg_unit_on;
    boolean kg_unit_chosen;
    CountBMIForKgM bmiForKgM = new CountBMIForKgM();
    CountBMIForKgM bmiForLbFt = new CountBMIForLbFt();
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2);

        preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        kg_unit_chosen = true;
        count_bmi_button = (Button)findViewById(R.id.bmi_calc_button);
        weight_input = (EditText)findViewById(R.id.input_weight_box);
        height_input = (EditText)findViewById(R.id.input_height_box);
        result_label = (TextView)findViewById(R.id.bmi_result);
        kg_unit_on = (Switch)findViewById(R.id.unit_switch);
        result_msg = (TextView)findViewById(R.id.bmi_result_msg);

        kg_unit_on.setOnClickListener(this);
        count_bmi_button.setOnClickListener(this);
        restoreData();
    }

    @Override
    public void onClick(View v) {
        if(v == count_bmi_button)
        {
            try {
                float weight = Float.valueOf(weight_input.getText().toString());
                float height = Float.valueOf(height_input.getText().toString());
                Float result = getBmiResult(height, weight);
                showResultLabel(result);
                changeBmiColor(result);
            }
            catch (Exception e) {

                showToast(getString(R.string.wrong_input));
            }
        }
        else if (v == kg_unit_on)
        {
            kg_unit_chosen = !kg_unit_chosen;
            switchStateChange();

        }
    }

    private void switchStateChange() {
        if(kg_unit_chosen) kg_unit_on.setText(R.string.unit_kg) ;
        else{ kg_unit_on.setText(R.string.unit_lb);}
        kg_unit_on.setChecked(!kg_unit_chosen);
    }

    private void showResultLabel(Float result) {
        result_label.setText(result.toString());
        result_msg.setText(R.string.bmi_calc_msg);
    }

    private float getBmiResult(float height, float weight)
    {
        float result;
        if(kg_unit_chosen) result= bmiForKgM.countBMI(weight, height);
        else {
            result = bmiForLbFt.countBMI(weight, height);
        }
        String resultformat = String.format(java.util.Locale.US, "%.2f", result);
        return Float.valueOf(resultformat);
    }

    private void showToast(String notification)
    {
        Toast.makeText(getApplicationContext(), notification, Toast.LENGTH_LONG).show();
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_save_data:
                saveData();
                showToast("Zapisano Dane");
                return true;
            case R.id.menu_about_author:
                Intent intent = new Intent(this, AboutAuthor.class);
                startActivity(intent);
                return true;
            case R.id.menu_social_share:
                shareBmi();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void shareBmi() {
        if(!result_label.getText().toString().equals(""))
        {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_myBmiIs).concat(
                    result_label.getText().toString()).concat(" " +
                    getString(R.string.app_footer)));
            startActivity(Intent.createChooser(sharingIntent, getString(R.string.social_share)));
        }
        else
        {
            showToast(getString(R.string.bmi_notCalculated));
        }
    }

    private void changeBmiColor(float result)
    {
        if(result > 30 || result < 18.5) {
            result_msg.setTextColor(Color.RED);
            result_label.setTextColor(Color.RED);
        }
        else if (result >= 18.5 && result < 25)
        {
            result_msg.setTextColor(Color.BLACK);
            result_label.setTextColor(Color.rgb(5, 252, 0));
        }
        else
        {
            result_msg.setTextColor(Color.BLACK);
            result_label.setTextColor(Color.rgb(185,242,0));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        // return true so that the menu pop up is opened
        return true;
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("kg_unit_chosen", kg_unit_chosen);
        savedInstanceState.putString("result", result_label.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        kg_unit_chosen = (boolean)savedInstanceState.get("kg_unit_chosen");
        kg_unit_on.setChecked(!kg_unit_chosen); //if not checked then kg choosen
        switchStateChange();
        String result = (String)savedInstanceState.get("result");
        if(!result.equals(""))
        {
            result_msg.setText(R.string.bmi_calc_msg);
            result_label.setText(result);
            changeBmiColor(Float.valueOf(result));
        }
    }

    private void restoreData()
    {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        height_input.setText(sharedPref.getString("height",""));
        weight_input.setText(sharedPref.getString("weight",""));
        kg_unit_chosen = sharedPref.getBoolean("kg_unit_chosen", true);
        switchStateChange();
    }
    private void saveData()
    {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("kg_unit_chosen", kg_unit_chosen);
        editor.putString("height", height_input.getText().toString());
        editor.putString("weight", weight_input.getText().toString());
        editor.commit();
    }
}