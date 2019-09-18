package com.example.youtube;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {


    EditText email, passWord;
    Button login, cancel;
    String RealUserEmail = "Ghedaa@gmail.com";
    String RealPassWord = "33333";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.userEmail);
        passWord = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.okBtn);
        login.setOnClickListener(this);
        cancel = (Button) findViewById(R.id.cancelBtn);
        cancel.setOnClickListener(this);



    }
    @Override
    public void onPause () {
        super.onPause();
        finish();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.okBtn:
                //get userinput from Edit text
                String userNameInput = email.getText().toString();
                String passWordInput = passWord.getText().toString();
                String emailIn = email.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                //comparision________________
                if (userNameInput.equalsIgnoreCase(RealUserEmail) && passWordInput.equalsIgnoreCase(RealPassWord)&& emailIn.matches(emailPattern)) {
                    //show a popup for result
                    Toast.makeText(getApplicationContext(), "success!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                    startActivity(intent);
                }//end if

                else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

                    // Setting Dialog Title
                    alertDialog.setTitle("Error");

                    // Setting Dialog Message
                    alertDialog.setMessage("Invalid Email or Password, please try again!!");

                    // Setting Icon to Dialog
                    alertDialog.setIcon(R.drawable.error);

                    // Setting Positive "Yes" Button
                    alertDialog.setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // User pressed YES button. Write Logic Here
                            Toast.makeText(getApplicationContext(), "Invalid Email or Password, please try again",
                                    Toast.LENGTH_SHORT).show();
                            email.setText("");
                            passWord.setText("");
                        }
                    });


                    // Setting Netural "Cancel" Button
                    alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // User pressed Cancel button. Write Logic Here
                            Toast.makeText(getApplicationContext(), "You clicked on Cancel",
                                    Toast.LENGTH_SHORT).show();


                            Intent intent = new Intent(MainActivity.this, SplashActivity.class); // from where? and the distanation
                            startActivity(intent); // to start another activity
                        }
                    });

                    // Showing Alert Message
                    alertDialog.show();


                } //end else
                break;

            case R.id.cancelBtn:
                email.setText("");
                passWord.setText("");
                break;
        }
    }
}
