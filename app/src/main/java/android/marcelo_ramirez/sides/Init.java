package android.marcelo_ramirez.sides;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by gonzalopro on 8/1/16.
 */
public class Init extends Application {

    String ci_user;

    @Override
    public void onCreate() {
        super.onCreate();

        ActiveAndroid.initialize(this);
    }

    public String getCi_user() {
        return ci_user;
    }

    public void setCi_user(String ci_user) {
        this.ci_user = ci_user;
    }
}
