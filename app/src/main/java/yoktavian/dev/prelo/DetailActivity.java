package yoktavian.dev.prelo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import yoktavian.dev.prelo.models.DataDetail;
import yoktavian.dev.prelo.models.DataModel;
import yoktavian.dev.prelo.networking.ApiRequest;

public class DetailActivity extends AppCompatActivity {

    private ApiRequest api;
    private ImageView img_photo;
    private CircleImageView img_profile;
    private TextView txt_title, txt_price, txt_name, txt_email;
    private ProgressBar progressBar;
    private RelativeLayout barData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        img_photo   = (ImageView) findViewById(R.id.img_photo);
        img_profile = (CircleImageView) findViewById(R.id.img_profil);
        txt_email   = (TextView) findViewById(R.id.txt_email);
        txt_price   = (TextView) findViewById(R.id.txt_price);
        txt_name    = (TextView) findViewById(R.id.txt_name);
        txt_title   = (TextView) findViewById(R.id.txt_title);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        barData     = (RelativeLayout) findViewById(R.id.barData);

        Intent i = getIntent();
        String id = i.getStringExtra("id");
        Log.d("res", id);

        api = new ApiRequest(DetailActivity.this);
        api.getDetail(id);
    }

    public void _onRequestSuccess(DataDetail data){
        progressBar.setVisibility(View.GONE);
        barData.setVisibility(View.VISIBLE);
        txt_email.setText(data.getData().getSeller().getEmail());
        txt_price.setText("Rp. "+data.getData().getPrice());
        txt_title.setText(data.getData().getName());
        txt_name.setText(data.getData().getSeller().getFullname());
        Glide.with(DetailActivity.this).load(data.getData().getDisplay().get(0)).into(img_photo);
        Glide.with(DetailActivity.this).load(data.getData().getSeller().getPict()).into(img_profile);
    }

    public void _onRequestFailed(){
        progressBar.setVisibility(View.GONE);
        showNotice("Get data failed");
    }

    public void _onSomeThingWrong(){
        progressBar.setVisibility(View.GONE);
        showNotice("Error");
    }

    private void showNotice(String msg){
        Toast.makeText(DetailActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
