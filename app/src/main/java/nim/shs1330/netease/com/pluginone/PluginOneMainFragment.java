package nim.shs1330.netease.com.pluginone;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;


public class PluginOneMainFragment extends Fragment {

    private static final String TAG = "PluginOneMainFragment";
    public PluginOneMainFragment() {
    }

    public static PluginOneMainFragment newInstance() {
        
        PluginOneMainFragment fragment = new PluginOneMainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = null;
        try {
            Field mContextField = LayoutInflater.class.getDeclaredField("mContext");
            mContextField.setAccessible(true);
            Context original = (Context) mContextField.get(inflater);
            mContextField.set(inflater, PluginOneApp.getSourceContext());
            root = inflater.inflate(R.layout.fragment_plugin_one_main, container, false);
            mContextField.set(inflater, original);

            root.findViewById(R.id.tv_info).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("nim.shs1330.netease.com.pluginone",
                            "nim.shs1330.netease.com.pluginone.MainActivity"));
                    startActivity(intent);
                }
            });
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return root;
    }

}
