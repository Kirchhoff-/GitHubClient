package com.kirchhoff.example.githubclient.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Kirchhoff-
 */

public class RepositoriesActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, RepositoriesActivity.class);
        context.startActivity(intent);
    }
}
