package com.dsss.ankush.shlokaabhyasanupdated;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends AppCompatActivity {
     EditText email,password,no,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final ProgressDialog progressDialog=new ProgressDialog(SignUpActivity.this);
        progressDialog.setTitle("Alert");
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(false);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password) ;
        no=(EditText)findViewById(R.id.mobile);
        name=(EditText)findViewById(R.id.name);
        Button signup=(Button)findViewById(R.id.signupbutton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if(check())
                {
                    progressDialog.show();
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            // authResult.getUser().updateProfile(dis)
                            UserProfileChangeRequest userProfileChangeRequest=new UserProfileChangeRequest.Builder().setDisplayName(name.getText().toString()).build();

                                   authResult.getUser().updateProfile(userProfileChangeRequest);
                                   progressDialog.dismiss();
                            Snackbar.make(view,"Succesfully created",Snackbar.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Snackbar.make(view,e.getMessage(),Snackbar.LENGTH_LONG).show();

                        }
                    });
                }
                else
                {
                    Snackbar.make(view,"Every Feild is necessary",Snackbar.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }
        });
        Button google=(Button)findViewById(R.id.google);
        google.setVisibility(View.GONE);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Button facebok=(Button)findViewById(R.id.facebook );
        facebok.setVisibility(View.GONE);
        facebok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();

            }
        });
    }
    boolean check()
    {
        if(TextUtils.isEmpty(email.getText().toString())||TextUtils.isEmpty(password.getText().toString())||TextUtils.isEmpty(no.getText().toString())||TextUtils.isEmpty(name.getText().toString()))
        {
            return false;
        }
        {
            return true;
        }
    }
}
