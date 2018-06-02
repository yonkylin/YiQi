package yonky.yiqi.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.util.HashSet;
import java.util.Set;

public class App extends Application {
    private Set<Activity> allActivities;
    private static App instance;
    public static synchronized App getInstance(){
        return instance;
    }
    private RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        refWatcher=LeakCanary.install(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
        instance=this;
    }

    public static RefWatcher getRefWatcher(Context context) {

        return getInstance().refWatcher;
    }



    public void addActivity(Activity act){
        if(allActivities ==null){
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act){
        if(allActivities!=null){
            allActivities.remove(act);
        }
    }

    public void exitApp(){
        if(allActivities!=null){
            synchronized (allActivities){
                for(Activity act:allActivities){
                    act.finish();
                }
            }
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }
}
