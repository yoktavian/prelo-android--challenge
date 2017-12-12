package yoktavian.dev.prelo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

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
    private ApiRequest apiRequest;
    private LoginSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
        session = new LoginSession(HomeActivity.this);
        Glide.with(HomeActivity.this).load(session.getSession_url_avatar()).into(avatar);
        txt_fullname.setText(session.getSes_fullname());
        txt_username.setText(session.getSes_username());
        txt_email.setText(session.getSes_email());
        txt_address.setText(session.getSes_alamat());
    }

    private void getLoveDataList(){
        apiRequest = new ApiRequest(HomeActivity.this);
        apiRequest.getLoveList(session.getSes_token());
    }

    private void setData(){
        adapter = new AdapterLoveList(HomeActivity.this, data);
        recyclerView.setAdapter(adapter);
    }

    public void _onRequestSuccess(ArrayList<LoveListModel> data){
        this.data = data;
        setData();
    }

    public void _onRequestFailed(){

    }

    public void _onError(){

    }
}
