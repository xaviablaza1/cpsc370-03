package edu.chapman.cpsc370.asdplaydate;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;

import edu.chapman.cpsc370.asdplaydate.models.ASDPlaydateUser;
import edu.chapman.cpsc370.asdplaydate.models.Broadcast;
import edu.chapman.cpsc370.asdplaydate.models.Child;
import edu.chapman.cpsc370.asdplaydate.models.Conversation;
import edu.chapman.cpsc370.asdplaydate.models.Message;

public class BaseApplication extends Application
{
    private static final boolean DEBUG = true;

    public BaseApplication()
    {
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        setupParse();
    }

    public void setupParse()
    {
        ParseUser.registerSubclass(ASDPlaydateUser.class);
        ParseObject.registerSubclass(Child.class);
        ParseObject.registerSubclass(Broadcast.class);
        ParseObject.registerSubclass(Conversation.class);
        ParseObject.registerSubclass(Message.class);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key));
        ParseInstallation.getCurrentInstallation().saveInBackground();

        if (!DEBUG)
            ParseUser.enableRevocableSessionInBackground();
    }

    public static boolean inDEBUGMode()
    {
        return DEBUG;
    }

    private static boolean activityVisible;

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }


}

