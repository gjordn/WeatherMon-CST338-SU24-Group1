package com.example.weathermon;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.weathermon.database.WeathermonRepository;
import com.example.weathermon.database.entities.User;

public class AdminActivity extends AppCompatActivity {

    private static final String TAG = "AdminActivity";
    private WeathermonRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        repository = WeathermonRepository.getRepository(getApplication());

        Button createUserButton = findViewById(R.id.createUserButton);
        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreateUserDialog();
            }
        });

        Button deleteUserButton = findViewById(R.id.deleteUserButton);
        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteUserDialog();
            }
        });

        Button changePasswordButton = findViewById(R.id.changePasswordButton);
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangePasswordDialog();
            }
        });

        Button makeAdminButton = findViewById(R.id.makeAdminButton);
        makeAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeAdmin();
            }
        });
    }

    private void showCreateUserDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Create User");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.create_user, (ViewGroup) findViewById(android.R.id.content), false);

        final EditText inputUsername = viewInflated.findViewById(R.id.inputUsername);
        final EditText inputPassword = viewInflated.findViewById(R.id.inputPassword);
        final EditText inputIsAdmin = viewInflated.findViewById(R.id.inputIsAdmin);

        builder.setView(viewInflated);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String username = inputUsername.getText().toString();
                String password = inputPassword.getText().toString();
                boolean isAdmin = Boolean.parseBoolean(inputIsAdmin.getText().toString());
                Log.d(TAG, "Creating user: " + username + ", isAdmin: " + isAdmin);
                createUser(username, password, isAdmin);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void showDeleteUserDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete User");

        final EditText inputUsername = new EditText(this);
        builder.setView(inputUsername);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String username = inputUsername.getText().toString();
                Log.d(TAG, "Deleting user: " + username);
                deleteUser(username);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void showChangePasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change Password");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_change_password, (ViewGroup) findViewById(android.R.id.content), false);

        final EditText inputUsername = viewInflated.findViewById(R.id.inputUsername);
        final EditText inputNewPassword = viewInflated.findViewById(R.id.inputNewPassword);

        builder.setView(viewInflated);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String username = inputUsername.getText().toString();
                String newPassword = inputNewPassword.getText().toString();
                Log.d(TAG, "Changing password for user: " + username);
                changeUserPassword(username, newPassword);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void createUser(String username, String password, boolean isAdmin) {
        User user = new User(username, password, isAdmin);
        repository.insertUser(user);
        Log.d(TAG, "User created: " + username);
        Toast.makeText(this, "User created successfully", Toast.LENGTH_SHORT).show();
    }

    private void deleteUser(String username) {
        repository.deleteUserByUsername(username);
        Log.d(TAG, "User deleted: " + username);
        Toast.makeText(this, "User deleted successfully", Toast.LENGTH_SHORT).show();
    }

    private void changeUserPassword(String username, String newPassword) {
        repository.updateUserPassword(username, newPassword);
        Log.d(TAG, "Password changed for user: " + username);
        Toast.makeText(this, "Password changed successfully", Toast.LENGTH_SHORT).show();
    }

    private void makeAdmin() {
        // Open a dialog or a new activity to get the username and make the user an admin
        Toast.makeText(this, "Make Admin button clicked", Toast.LENGTH_SHORT).show();
    }

    private void changePassword() {
        // Open a dialog or a new activity to get the username and change the user's password
        Toast.makeText(this, "Change Password button clicked", Toast.LENGTH_SHORT).show();
    }

    static Intent adminActivityIntentFactory(Context context) {
        return new Intent(context, AdminActivity.class);
    }
}


