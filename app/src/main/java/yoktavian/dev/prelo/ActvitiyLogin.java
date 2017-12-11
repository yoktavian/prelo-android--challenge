package yoktavian.dev.prelo;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import yoktavian.dev.prelo.models.DataModel;
import yoktavian.dev.prelo.models.DataUser;
import yoktavian.dev.prelo.networking.ApiRequest;

public class ActvitiyLogin extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private EditText edttext_email, edttext_password;

    ApiRequest api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actvitiy_login);

        relativeLayout              = (RelativeLayout) findViewById(R.id.relativeLayout);
        edttext_email      = (EditText) findViewById(R.id.edittext_email);
        edttext_password   = (EditText) findViewById(R.id.edittext_password);
        Button button_login         = (Button) findViewById(R.id.button_login);
        api = new ApiRequest(this);

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
                displayNotice("Password minimal 6 karakter");
            }
        } else {
            displayNotice("Email/username minimal 4 karakter");
        }
        return false;
    }

    private void displayNotice(String message){
        Snackbar snackbar = Snackbar
                .make(relativeLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void _doLogin(String username, String password){
        Log.d("res", "login");
        api.doLogin(username, password);
    }

    public void _onLoginSuccess(DataModel data){
        Log.d("res", data.getProfile().getPict());
    }

    public void _onLoginFailed(){
        displayNotice("User dan password salah!");
    }

    public void _onSomeThingWrong(){
        displayNotice("Error!");
    }
}
