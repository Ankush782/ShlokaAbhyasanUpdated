package com.dsss.ankush.shlokaabhyasanupdated;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(!(FirebaseAuth.getInstance().getCurrentUser()==null))
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        else
        {

        }

        email=(EditText)findViewById(R.id.login_email);
        password=(EditText)findViewById(R.id.login_password);
        login=(Button)findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
               FirebaseAuth.getInstance().signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Snackbar.make(view,e.getMessage(),Snackbar.LENGTH_LONG).show();

                      // finish();


                   }
               }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                   @Override
                   public void onSuccess(AuthResult authResult)
                   {
                       Snackbar.make(view,"Succesfully Login",Snackbar.LENGTH_LONG).show();
                       startActivity(new Intent(getApplicationContext(),MainActivity.class));
                       finish();

                   }
               });
            }
        });
        TextView textView=(TextView)findViewById(R.id.forgot);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(email.getText().toString())||(!email.getText().toString().contains("@"))||(!email.getText().toString().contains(".")))
                {
                    Snackbar.make(view,"Please enter a valid email",Snackbar.LENGTH_LONG).show();

                }
                else
                {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email.getText().toString());
                    Snackbar.make(view,"Password reset email sent",Snackbar.LENGTH_LONG).show();

                }}
        });
        TextView t=(TextView)findViewById(R.id.signup);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
            }
        });

    }
}
