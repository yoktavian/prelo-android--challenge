package yoktavian.dev.prelo;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import yoktavian.dev.prelo.models.DataModel;
import yoktavian.dev.prelo.models.DataUser;
import yoktavian.dev.prelo.networking.ApiRequest;
import yoktavian.dev.prelo.session.LoginSession;

public class ActvitiyLogin extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private EditText edttext_email, edttext_password;
    ApiRequest api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actvitiy_login);
        Log.d("res","oncreate");

        relativeLayout              = (RelativeLayout) findViewById(R.id.relativeLayout);
        edttext_email               = (EditText) findViewById(R.id.edittext_email);
        edttext_password            = (EditText) findViewById(R.id.edittext_password);
        Button button_login         = (Button) findViewById(R.id.button_login);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edttext_email.getText().toString();
                String password = edttext_password.getText().toString();
                if (isInputValid(username, password)){
                    _doLogin(username, password);
                }
            }
        });
    }

    private boolean isInputValid(String email, String password){
        if(email.length()>=4 && !email.equals("")){
            if(password.length()>=6 && !password.equals("")){
                return true;
            } else {
                showNotice("Password minimal 6 karakter");
            }
        } else {
            showNotice("Email/username minimal 4 karakter");
        }
        return false;
    }

    private void showNotice(String message){
        Snackbar snackbar = Snackbar
                .make(relativeLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void _doLogin(String username, String password){
        api.doLogin(username, password);
    }

    public void _onLoginSuccess(DataModel data){
        Toast.makeText(ActvitiyLogin.this,"Login Success", Toast.LENGTH_SHORT).show();

        // set session login
        LoginSession session = new LoginSession(ActvitiyLogin.this);
        session.setLoginSession(data.getFullname(), data.getUsername(), data.getEmail(), data.getToken(),
                data.getAddress().getSubdistrict_name()+", "
                        +data.getAddress().getRegion_name()+", " +data.getAddress().getProvince_name(), data.getProfile().getPict());

        // clear input
        clearEditText();

        // go to home
        Intent i = new Intent(ActvitiyLogin.this, HomeActivity.class);
        startActivity(i);
    }

    private void clearEditText(){
        edttext_email.setText("");
        edttext_password.setText("");
    }

    public void _onLoginFailed(){
        showNotice("User dan password salah!");
    }

    public void _onSomeThingWrong(){
        showNotice("Error!");
    }

    @Override
    public void onResume(){
        super.onResume();
        api = new ApiRequest(this);
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
