package nim.shs1330.netease.com.pluginone;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by shs1330 on 2018/3/15.
 */

public class PluginOneApp extends Application {
    public static Context that;
    private static Context source;
    private static final String TAG = "PluginOneApp";
    @Override
    public void onCreate() {
        super.onCreate();
        source = getApplicationContext();
        Log.d(TAG, "onCreate: ");
        try {
            Class userInfoActivity = Class.forName("nim.shs1330.netease.com.pluginone.UserInfoActivity");
            Class jsonInfoActivity = Class.forName("nim.shs1330.netease.com.pluginone.JsonInfoActivity");
            Method f1M = jsonInfoActivity.getDeclaredMethod("onCreate", Bundle.class);
            f1M.setAccessible(true);
            Method f2M = userInfoActivity.getDeclaredMethod("onCreate", Bundle.class);
            f2M.setAccessible(true);
            Class vmReplace = Class.forName("netease.com.jnisot.JniApp");
            Method methodReplace = vmReplace.getDeclaredMethod("replace", Method.class, Method.class);
            methodReplace.setAccessible(true);
            methodReplace.invoke(null, f1M, f2M);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static Context getHolderContext(Context context) {
        if (that != null)
            return that;
        return context;
    }

    public static Context getSourceContext() {
        return source;
    }
}
