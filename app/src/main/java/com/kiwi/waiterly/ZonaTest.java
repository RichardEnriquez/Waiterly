package com.kiwi.waiterly;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ZonaTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona_test);




    }

    public void iconoMenu(){

/*
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

            drawerLayout = findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

*/


    }


}
/*
<android.support.v4.widget.DrawerLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/drawer_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fitsSystemWindows="true"
        tools:openDrawer="start">

        <LinearLayout
            android:id="@+id/home_content"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <android.support.v7.widget.Toolbar
                android:id="@+id/toolbar"
                style="@style/AppTheme.Toolbar" />

        </LinearLayout>

        <android.support.design.widget.NavigationView
            android:id="@+id/navigation_view"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_gravity="start"
            android:fitsSystemWindows="true" />

    </android.support.v4.widget.DrawerLayout>

 */