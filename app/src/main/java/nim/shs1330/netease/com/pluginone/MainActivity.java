package nim.shs1330.netease.com.pluginone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    final public String f1()
    {
        return "this is a bug Toast";
    }
    final public String f2()
    {
        return "this is a fixed Toast";
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt_send1).setOnClickListener(this);
        findViewById(R.id.bt_send2).setOnClickListener(this);
        findViewById(R.id.bt_toinfo).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_send1) {
            Toast.makeText(PluginOneApp.getHolderContext(this), f1(), Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == R.id.bt_send2){

            try {
                Class vmReplace = Class.forName("netease.com.jnisot.VMReplace");

                Method methodReplace = vmReplace.getDeclaredMethod("showArtMethodSize", Method.class, Method.class);
                methodReplace.setAccessible(true);
                Method f1M = MainActivity.class.getDeclaredMethod("f1");
                Method f2M = MainActivity.class.getDeclaredMethod("f2");
                methodReplace.invoke(null, f1M, f2M);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if (v.getId() == R.id.bt_toinfo) {
            Log.d(TAG, "onClick: ccccc");
            startActivity(new Intent(this, JsonInfoActivity.class));
        }
    }
}
