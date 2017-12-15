package yoktavian.dev.prelo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CheckoutActivity extends AppCompatActivity {

    private TextView txt_id, txt_total, txt_time;
    private CardView cardView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txt_id      = (TextView)findViewById(R.id.txt_id);
        txt_total   = (TextView)findViewById(R.id.txt_total);
        txt_time    = (TextView)findViewById(R.id.txt_time);
        cardView    = (CardView)findViewById(R.id.cardviewData);

        // loading
        cardView.setVisibility(View.GONE);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Intent i = getIntent();
        txt_id.setText(i.getStringExtra("_id"));
        txt_total.setText("Rp. "+ i.getStringExtra("price"));
        txt_time.setText(i.getStringExtra("expire_time"));

        // load data complete
        progressBar.setVisibility(View.GONE);
        cardView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
