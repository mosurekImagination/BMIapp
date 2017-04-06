package net.mosur.tomasz.lab2.lab2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Tomek on 05/04/2017.
 */

public class AboutAuthor extends AppCompatActivity implements View.OnClickListener {

    TextView website;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_author);
        website = (TextView)findViewById(R.id.website_url_button);
        setTitle(getText(R.string.about_author));
       website.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://mosur.net"));
        startActivity(intent);
    }

}
