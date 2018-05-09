package yonky.yiqi.base;

import android.app.Activity;
import android.app.Application;

import java.util.HashSet;
import java.util.Set;

public class App extends Application {
    private Set<Activity> allActivities;
    private static App instance;
    public static synchronized App getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
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
