package com.devleo.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.review.ReviewException;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.model.ReviewErrorCode;

public class ReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_review);

        View mainView = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        showReviewDialog();

    }

    private void showReviewDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_review_prompt, null);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        Button btnLater = dialogView.findViewById(R.id.btn_later);
        Button btnReview = dialogView.findViewById(R.id.btn_review);

        btnLater.setOnClickListener(v -> {
            dialog.dismiss();
            Toast.makeText(this, "다음에 다시 요청드릴게요!", Toast.LENGTH_SHORT).show();
        });

        btnReview.setOnClickListener(v -> {
            dialog.dismiss();
            // Play Store 리뷰 페이지로 이동
//            try {
//                Uri uri = Uri.parse("market://details?id=" + getPackageName());
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
//            } catch (ActivityNotFoundException e) {
//                // Play Store 앱이 없는 경우 웹 브라우저로 열기
//                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName());
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
//            }
        });

        dialog.show();
    }


}