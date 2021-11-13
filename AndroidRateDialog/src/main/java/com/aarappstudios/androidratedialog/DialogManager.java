package com.aarappstudios.androidratedialog;



import static com.aarappstudios.androidratedialog.PreferenceHelper.setAgreeShowDialog;
import static com.aarappstudios.androidratedialog.PreferenceHelper.setRemindInterval;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.aarappstudios.androidratedialog.R;


class DialogManager {



    private DialogManager()
    {
    }

    static float rate=10;

    static Dialog create(final Context context, final DialogOptions options)
    {

        LayoutInflater factory = LayoutInflater . from (context);
        final View deleteDialogView = factory.inflate(R.layout.dialog_review, null);
        final AlertDialog deleteDialog = new AlertDialog . Builder (context).create();
        deleteDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);
        deleteDialog.setView(deleteDialogView);
        RatingBar ratingBar = deleteDialogView . findViewById (R.id.ratingbar);
        Button rateNowBtn = deleteDialogView . findViewById (R.id.rate_now_button);
        TextView mayBeLater = deleteDialogView . findViewById (R.id.may_be_later_tv);

        mayBeLater.setOnClickListener(v -> {
        setRemindInterval(context);
        String thanksfeedback = "Okey";
        Toast.makeText(context, thanksfeedback,
                Toast.LENGTH_LONG).show();
        deleteDialog.dismiss();
    });


        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
        rate = rating;
        Log.d("TAG", "onRatingChanged: " + rating);
    });

        rateNowBtn.setOnClickListener(v -> {

        if (rate == 10f) {
            String thanksfeedback = "Please give rating above !!";
            Toast.makeText(context, thanksfeedback,
                    Toast.LENGTH_LONG).show();
            deleteDialog.dismiss();
        } else {
            deleteDialog.dismiss();
            if (ratingBar.getRating() == 5) {
                RateUS(context);
                setAgreeShowDialog(context, false);
                String thanksfeedback = "Please give rating in playstore !!";
                Toast.makeText(context, thanksfeedback,
                        Toast.LENGTH_LONG).show();
            } else {
                setAgreeShowDialog(context, false);

                String thanksfeedback = "Thank you for rating  !!";
                Toast.makeText(context, thanksfeedback,
                        Toast.LENGTH_LONG).show();             }
        }
    });

        return deleteDialog;
    }





    static void RateUS(Context context)
    {
        Uri uri = Uri . parse ("market://details?id=" + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {

            context.startActivity(goToMarket);

        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent (Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }


    }
}