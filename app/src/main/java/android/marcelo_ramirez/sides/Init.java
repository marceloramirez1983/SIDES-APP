package android.marcelo_ramirez.sides;

import android.app.Application;
import android.os.SystemClock;

import com.activeandroid.ActiveAndroid;

import java.util.concurrent.TimeUnit;

/**
 * Created by gonzalopro on 8/1/16.
 */
public class Init extends Application {

    String ci_user;

    @Override
    public void onCreate() {
        super.onCreate();

        SystemClock.sleep(TimeUnit.SECONDS.toMillis(3));
        ActiveAndroid.initialize(this);
    }

    public String getCi_user() {
        return ci_user;
    }

    public void setCi_user(String ci_user) {
        this.ci_user = ci_user;
    }
}
