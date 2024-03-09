package com.example.week6_nhom6_advertisingid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.week6_nhom6_advertisingid.regCallbacks.ScenicSpotsReqCallback;

import org.chromium.net.CronetEngine;
import org.chromium.net.UrlRequest;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;

public class SceSpotsDetails extends AppCompatActivity {

    public int currentId = 1;
    public String text = "";

    private JSONArray imgUrls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sce_spots_details);

        Bundle extras = getIntent().getExtras();
        currentId = Integer.parseInt(extras.getString("current_id"));
        sceSpotsShowDetail(String.valueOf(currentId));
    }

    public void sceSpotsShowDetail(String id) {

        TextView locTxtView = findViewById(R.id.locDetail);
        TextView provinceTxtView = findViewById(R.id.provinceDetail);
        TextView partTxtView = findViewById(R.id.partDetail);
        TextView contentTxtView = findViewById(R.id.contentDetail);

        CronetEngine.Builder builder = new CronetEngine.Builder(this.getBaseContext());
        CronetEngine engine = builder.build();

        String svrUrl = "http://192.168.1.10:8080/place/getdetail?id=" + id;
        UrlRequest urlReq = engine.newUrlRequestBuilder(svrUrl, new ScenicSpotsReqCallback(
                        jsonObject -> {
                            try {
                                String loc = jsonObject.getString("loc");
                                String province = jsonObject.getString("province");
                                String part = jsonObject.getString("part");
                                String content = jsonObject.getString("content");
                                JSONArray imgUrls = jsonObject.getJSONArray("imgUrls"); // Di chuyển đoạn này vào callback
                                locTxtView.setText(loc);
                                provinceTxtView.setText(province);
                                partTxtView.setText(part);
                                contentTxtView.setText(content);

                                runOnUiThread(() -> {
                                    try {
                                        for (int i = 0; i < imgUrls.length(); i++) {
                                            String imgUrl = imgUrls.getString(i);
                                            ImageView imageView = findViewById(R.id.imgContainer);
                                            Glide.with(this).load(imgUrl).into(imageView);
                                            // Đặt thuộc tính layout params và thêm imageView vào layout của bạn
                                            // Ví dụ:
                                            // LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                            // parentLayout.addView(imageView, layoutParams);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                });
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                ), Executors.newSingleThreadExecutor())
                .setHttpMethod("GET").build();
        urlReq.start();
    }

    public void onBackClick(View view) {
        if(Objects.equals(currentId, 1))
            currentId=5;
        else
            currentId-=1;
        sceSpotsShowDetail(String.valueOf(currentId));
    }

    public void onNextClick(View view) {
        if(Objects.equals(currentId, 5))
            currentId=1;
        else
            currentId+=1;
        sceSpotsShowDetail(String.valueOf(currentId));
    }
}