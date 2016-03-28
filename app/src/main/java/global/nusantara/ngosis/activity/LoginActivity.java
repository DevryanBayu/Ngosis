package global.nusantara.ngosis.activity;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import global.nusantara.ngosis.R;

public class LoginActivity extends AppCompatActivity {
    private static String KEY_SUCCESS = "success";
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private EditText _emailText;
    private EditText _passwordText;
    private Button _loginButton;
    private TextView _signupLink;
    
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.setStatusBarColor(this.getResources().getColor(R.color.ColorPrimaryDark));
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        setContentView(R.layout.activity_login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        init();
        
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
//                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
//                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    private void init() {
        _emailText = (EditText) findViewById(R.id.input_email);
        _passwordText = (EditText) findViewById(R.id.input_password);
        _loginButton = (Button) findViewById(R.id.btn_login);
        _signupLink = (TextView) findViewById(R.id.link_signup);
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(true);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

//        userFunction = new UserFunctions(getApplicationContext());
//        json = userFunction.loginUser(email, password);
//        HttpClient httpClient = new DefaultHttpClient();
//        HttpPost httpPost = new HttpPost(url.urlLogin);
//        Log.e("tuwo","1");
//        try{
//            HttpResponse httpResponse = httpClient.execute(httpPost);
//            StatusLine statusLine = httpResponse.getStatusLine();
//            if(statusLine.getStatusCode() == 200){
//                try {
//                    if (json.getString(KEY_SUCCESS) != null) {
//                        Toast.makeText(this, "apalah apalah", Toast.LENGTH_SHORT).show();
//                        Log.e("tuwo","2");
//                        String res = json.getString(KEY_SUCCESS);
//                        if(Integer.parseInt(res) == 1){
//                            String user = json.getString("user");
//                            JSONObject jsonUser = new JSONObject(user);
//                            dbUser.open();
//                            Log.i("tuwo", jsonUser.getString("acc_id") + " " + jsonUser.getString("acc_username") + " " + jsonUser.getString("acc_profile"));
//                            dbUser.insert(jsonUser.getString("acc_id"), jsonUser.getString("acc_username"), jsonUser.getString("acc_profile"));
//                            dbUser.close();
//
//                            new android.os.Handler().postDelayed(
//                                    new Runnable() {
//                                        public void run() {
//
//                                            onLoginSuccess();
//                                            onLoginFailed();
//                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                            startActivity(intent);
//                                            progressDialog.dismiss();
//                                        }
//                                    }, 3000);
//
//                        }else{
//                            Toast.makeText(this, "User Id dan pasword tidak cocok", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }catch (NullPointerException e){
//                    e.printStackTrace();
//                    Toast.makeText(this, "User Id dan paswword tidak cocok", Toast.LENGTH_SHORT).show();
//                }
//            }else{
//                Toast.makeText(this, "Tidak dapat menyambung ke server", Toast.LENGTH_SHORT).show();
//            }
//        }
//        catch (IOException e) {
//            Toast.makeText(getBaseContext(), "Tidak dapat menyambung ke server", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }

        new android.os.Handler().postDelayed(
            new Runnable() {
                public void run() {

                    onLoginSuccess();
//                    onLoginFailed();
                    Intent intent = new Intent(getApplicationContext(), MainKepalaSekolahActivity.class);
                    startActivity(intent);
//                    progressDialog.dismiss();
                }
            }, 1000);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 20) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
