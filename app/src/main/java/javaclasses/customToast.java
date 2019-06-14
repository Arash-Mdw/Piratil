package javaclasses;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import arshmdw.piratil.R;

/**
 * Created by Arash on 6/14/2019.
 */

public class customToast extends Application{
    Context context;
    public static final int danger = 1;
    public static final int info = 2;
    public static final int succ = 3;
    public static final int alert = 4;


    public static final int Top = 1;
    public static final int Bottom = 2;
    String Text;
    ImageView imageView;
    TextView textView;
    LinearLayout linearLayout;

    public Toast getToast() {
        return toast;
    }

    Toast toast;
    public customToast(Context context, String text, int modelToast, int position) {
        this.context = context;
        Text = text;
        toast = new Toast(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.toast_theme,null);
        imageView = (ImageView) view.findViewById(R.id.imgAlert);
        textView = (TextView) view.findViewById(R.id.textAlert);
        linearLayout = (LinearLayout) view.findViewById(R.id.ToastParent);





        textView.setText(text);


        switch (position){
            case Top:
                toast.setGravity(0,0,-500);
                break;
            case Bottom:
                toast.setGravity(0,0,500);
                break;


        }

        switch (modelToast){
            case danger:
                imageView.setImageResource(R.drawable.ic_danger);
                linearLayout.setBackgroundResource(R.drawable.theme_danger);
                break;
            case info:
                imageView.setImageResource(R.drawable.ic_info);
                linearLayout.setBackgroundResource(R.drawable.theme_info);
                break;
            case succ:
                imageView.setImageResource(R.drawable.ic_succ);
                linearLayout.setBackgroundResource(R.drawable.theme_succ);
                break;
            case alert:
                imageView.setImageResource(R.drawable.ic_alert);
                linearLayout.setBackgroundResource(R.drawable.theme_alert);
                break;

        }
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
    }

}
