package yoktavian.dev.prelo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import yoktavian.dev.prelo.session.LoginSession;

public class HomeActivity extends AppCompatActivity {

    private TextView txt_fullname, txt_username, txt_email, txt_address;
    CircleImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        avatar          = (CircleImageView) findViewById(R.id.avatar);
        txt_fullname    = (TextView) findViewById(R.id.txt_fullname);
        txt_username    = (TextView) findViewById(R.id.txt_username);
        txt_email       = (TextView) findViewById(R.id.txt_email);
        txt_address     = (TextView) findViewById(R.id.txt_address);
        setDataUser();
    }

    private void setDataUser(){
        LoginSession session = new LoginSession(HomeActivity.this);
        Glide.with(HomeActivity.this).load(session.getSession_url_avatar()).into(avatar);
        txt_fullname.setText(session.getSes_fullname());
        txt_username.setText(session.getSes_username());
        txt_email.setText(session.getSes_email());
        txt_address.setText(session.getSes_alamat());
    }
}
