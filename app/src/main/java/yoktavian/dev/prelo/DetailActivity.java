package yoktavian.dev.prelo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import yoktavian.dev.prelo.models.DataCheckout;
import yoktavian.dev.prelo.models.DataDetail;
import yoktavian.dev.prelo.models.DataModel;
import yoktavian.dev.prelo.networking.ApiRequest;
import yoktavian.dev.prelo.session.LoginSession;

public class DetailActivity extends AppCompatActivity {

    private ApiRequest api;
    private ImageView img_photo;
    private CircleImageView img_profile;
    private TextView txt_title, txt_price, txt_name, txt_email;
    private ProgressBar progressBar;
    private RelativeLayout barData;
    private Button btn_beli;
    private LoginSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        img_photo   = (ImageView) findViewById(R.id.img_photo);
        img_profile = (CircleImageView) findViewById(R.id.img_profil);
        txt_email   = (TextView) findViewById(R.id.txt_email);
        txt_price   = (TextView) findViewById(R.id.txt_price);
        txt_name    = (TextView) findViewById(R.id.txt_name);
        txt_title   = (TextView) findViewById(R.id.txt_title);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        barData     = (RelativeLayout) findViewById(R.id.barData);
        btn_beli    = (Button) findViewById(R.id.btn_beli);
        session     = new LoginSession(DetailActivity.this);
        Intent i = getIntent();
        final String id = i.getStringExtra("id");
        Log.d("res", id);

        api = new ApiRequest(DetailActivity.this);
        api.getDetail(id);

        btn_beli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _doCheckout(id);
            }
        });
    }

    private void _doCheckout(String id){
        barData.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        // Body cart_products
        JSONObject jsonId = new JSONObject();
        try {
            jsonId.put("product_id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray json = new JSONArray();
        json.put(jsonId);

        // Body shipping_address
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("address", session.getSes_alamat());
        jsonObject.addProperty("province_id", session.getSes_provinceId());
        jsonObject.addProperty("region_id", session.getSes_regionId());
        jsonObject.addProperty("subdistrict_id", session.getSes_subdistrictId());
        jsonObject.addProperty("postal_code", session.getSes_postal());
        jsonObject.addProperty("recipient_name", session.getSes_fullname());
        jsonObject.addProperty("recipent_phone", session.getSes_phone());

        // post request
        Log.d("jsonarr", String.valueOf(json));
        Log.d("jsonobje", String.valueOf(jsonObject));
        api.doCheckout(session.getSes_token(), json, jsonObject);
    }

    public void _onRequestCheckoutSuccess(DataCheckout data) {
        progressBar.setVisibility(View.GONE);
        barData.setVisibility(View.VISIBLE);
        Intent i = new Intent(DetailActivity.this, CheckoutActivity.class);
        i.putExtra("_id", data.getData().getTransaction_id());
        i.putExtra("price", data.getData().getTotal_price());
        i.putExtra("expire_time", data.getData().getExpire_time());
        startActivity(i);
    }

    public void _onRequestCheckoutFailed() {
        barData.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        showNotice("Checkout failed");
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    // Tes
}
