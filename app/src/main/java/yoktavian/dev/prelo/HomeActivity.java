package yoktavian.dev.prelo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import yoktavian.dev.prelo.adapter.AdapterLoveList;
import yoktavian.dev.prelo.models.DataModel;
import yoktavian.dev.prelo.models.LoveListModel;
import yoktavian.dev.prelo.networking.ApiRequest;
import yoktavian.dev.prelo.session.LoginSession;

public class HomeActivity extends AppCompatActivity {

    private TextView txt_fullname, txt_username, txt_email, txt_address;
    private CircleImageView avatar;
    private RecyclerView recyclerView;
    private ArrayList<LoveListModel> data = new ArrayList<>();
    private AdapterLoveList adapter;
    private ApiRequest api;
    private LoginSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        session = new LoginSession(HomeActivity.this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(session.getSes_username());

        avatar          = (CircleImageView) findViewById(R.id.avatar);
        txt_fullname    = (TextView) findViewById(R.id.txt_fullname);
        txt_username    = (TextView) findViewById(R.id.txt_username);
        txt_email       = (TextView) findViewById(R.id.txt_email);
        txt_address     = (TextView) findViewById(R.id.txt_address);
        recyclerView    = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        setDataUser();
        getLoveDataList();
    }

    private void setDataUser(){
        Glide.with(HomeActivity.this).load(session.getSession_url_avatar()).into(avatar);
        txt_fullname.setText(session.getSes_fullname());
        txt_username.setText(session.getSes_username());
        txt_email.setText(session.getSes_email());
        txt_address.setText(session.getSes_alamat());
    }

    private void getLoveDataList(){
        api = new ApiRequest(HomeActivity.this);
        api.getLoveList(session.getSes_token());
    }

    private void setData(){
        adapter = new AdapterLoveList(HomeActivity.this, data, new AdapterLoveList.OnItemClickListener() {
            @Override
            public void onClick(LoveListModel model) {
                Intent i = new Intent(HomeActivity.this, DetailActivity.class);
                i.putExtra("id", model.getId());
                startActivity(i);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    public void _onRequestSuccess(ArrayList<LoveListModel> data){
        this.data = data;
        setData();
    }

    public void _onRequestFailed(){
        showNotice("Gagal mendapatkan data");
    }

    public void _onError(){
        showNotice("Error, something wrong!");
    }

    private void _doLogout(){
        session.doLogout();
        showNotice("Logout");
    }

    private void showNotice(String message){
        Toast.makeText(HomeActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                    _doLogout();
                    onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("res","resume");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d("res","pause");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d("res","stop");
    }
}
