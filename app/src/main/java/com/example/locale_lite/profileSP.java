package com.example.locale_lite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.service.Common;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class profileSP extends AppCompatActivity implements View.OnClickListener, RatingDialogListener {

    private LinearLayout review, call, message, directions;
    TextView Name, Category;
    ImageView DP;
    FloatingActionButton btnRating;
    RatingBar ratingBar;
    FirebaseDatabase database;
    DatabaseReference ratingTbl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_sp);
        database = FirebaseDatabase.getInstance();
        ratingTbl = database.getReference("Rating");
        review = findViewById(R.id.addreview);
        call = findViewById(R.id.call);
        Name = findViewById(R.id.name);
        Category = findViewById(R.id.category);
        DP = findViewById(R.id.dp);
        message = findViewById(R.id.message);
        directions = findViewById(R.id.directions);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        final String name = getIntent().getStringExtra("name");
        final String category = getIntent().getStringExtra("category");
        final String dpURL = getIntent().getStringExtra("dp");
        final String phone = getIntent().getStringExtra("phone");
        Picasso.with(profileSP.this).load(dpURL).into(DP);
        Name.setText(name);
        Category.setText(category);
        review.setOnClickListener(this);
        call.setOnClickListener(this);
        message.setOnClickListener(this);
        directions.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view == call) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + getIntent().getStringExtra("phone")));
            startActivity(intent);
        }
        if(view == review)
        {
            showRatingDialog();
        }

    }

    private void showRatingDialog() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(profileSP.this);
        View mView = getLayoutInflater().inflate(R.layout.rating_dialog, null);
        RatingBar rbar = mView.findViewById(R.id.ratingBar);
        EditText comment = mView.findViewById(R.id.comment);
        TextView submit = mView.findViewById(R.id.submit);
        TextView cancel = mView.findViewById(R.id.cancel);

        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();

        rbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(profileSP.this, "Stars" + v, Toast.LENGTH_SHORT).show();
            }
        });
    }




//        new AppRatingDialog.Builder()
//                .setPositiveButtonText("Submit")
//                .setNegativeButtonText("Cancel")
//                .setNoteDescriptions(Arrays.asList("Very Bad", "Not Good", "Quite Ok", "Very Good", "Excellent"))
//                .setDefaultRating(0)
//                .setTitle("Rate this Service Provider")
//                .setDescription("Please rate and give your feedback")
//                .setTitleTextColor(R.color.colorPrimary)
//                .setDescriptionTextColor(R.color.colorPrimary)
//                .setHint("Please write your comment here...")
//                .setHintTextColor(R.color.colorAccent)
//                .setCommentTextColor(android.R.color.white)
//                .setCommentBackgroundColor(R.color.colorPrimaryDark)
//                .setWindowAnimation(R.style.RatingDialogFadeAnim)
//                .create(profileSP.this)
//                .show();


    @Override
    public void onNegativeButtonClicked() {

    }

    @Override
    public void onPositiveButtonClicked(int value, String comments) {
    }
}