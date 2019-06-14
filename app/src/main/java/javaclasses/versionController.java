package javaclasses;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Arash on 6/12/2019.
 */

public class versionController {
    Boolean VerSion;
    Context context;
    public versionController(Boolean verSion, final Context context) {
        VerSion = verSion;
        if(!VerSion) {
            this.context = context;
            AlertDialog.Builder builder
                    = new AlertDialog.Builder(context);
            builder.setTitle("خطا");
            builder.setMessage("لطفا آخرین نسخه نرم افزار را دانلود نمایید");
            builder.setCancelable(false);
            builder.setPositiveButton("نصب", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                    context.startActivity(browserIntent);
                }
            });
            builder.show();
        }
    }
}
