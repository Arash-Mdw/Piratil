package javaclasses;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import arshmdw.piratil.R;


/**
 * Created by Arash on 6/10/2019.
 */

public class customSnackbar {



        String text;
        CoordinatorLayout parent;
        public static final int danger = 1;
        public static final int info = 2;
        public static final int succ = 3;
        public static final int alert = 4;

        public Snackbar getSnackbar() {
            return snackbar;
        }

        Snackbar snackbar;
        Context Context;
        public customSnackbar(String text, CoordinatorLayout parent, android.content.Context context, int model) {
            this.text = text;
            this.parent = parent;
            this.Context = context;

            snackbar = Snackbar.make(parent, text, Snackbar.LENGTH_INDEFINITE);

            final View snackBarView = snackbar.getView();

            TextView textView = (TextView) snackBarView.findViewById(R.id.snackbar_text);

            switch (model) {
                case danger:
                    snackBarView.setBackgroundColor(context.getResources().getColor(R.color.backDanger));
                    snackbar.setActionTextColor(context.getResources().getColor(R.color.textDanger));
                    textView.setTextColor(context.getResources().getColor(R.color.textDanger));

                    break;
                case info:

                    snackBarView.setBackgroundColor(context.getResources().getColor(R.color.backInfo));
                    snackbar.setActionTextColor(context.getResources().getColor(R.color.textInfo));
                    textView.setTextColor(context.getResources().getColor(R.color.textInfo));

                    break;
                case succ:

                    snackBarView.setBackgroundColor(context.getResources().getColor(R.color.backSucc));
                    snackbar.setActionTextColor(context.getResources().getColor(R.color.textSucc));
                    textView.setTextColor(context.getResources().getColor(R.color.textSucc));

                    break;
                case alert:

                    snackBarView.setBackgroundColor(context.getResources().getColor(R.color.backAlert));
                    snackbar.setActionTextColor(context.getResources().getColor(R.color.textAlert));
                    textView.setTextColor(context.getResources().getColor(R.color.textAlert));

                    break;

            }

        }
    }
