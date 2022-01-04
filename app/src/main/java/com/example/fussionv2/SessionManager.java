package com.example.fussionv2;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public SessionManager(Context ctx) {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences("fapp", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setLoggedIn(boolean loggedIn) {
        editor.putBoolean("loggedInMode", loggedIn);
        editor.commit();
    }

    public boolean loggedIn() {
        return prefs.getBoolean("loggedInMode", false);
    }
}
