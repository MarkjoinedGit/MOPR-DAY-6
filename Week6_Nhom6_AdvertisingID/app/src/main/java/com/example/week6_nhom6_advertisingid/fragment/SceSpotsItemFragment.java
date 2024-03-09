package com.example.week6_nhom6_advertisingid.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.week6_nhom6_advertisingid.R;
import com.example.week6_nhom6_advertisingid.fragment.placeholder.PlaceholderContent;
import com.example.week6_nhom6_advertisingid.regCallbacks.ScenicSpotsReqCallback;

import org.chromium.net.CronetEngine;
import org.chromium.net.UrlRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;

/**
 * A fragment representing a list of Items.
 */
public class SceSpotsItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SceSpotsItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SceSpotsItemFragment newInstance(int columnCount) {
        SceSpotsItemFragment fragment = new SceSpotsItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sce_spots_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(PlaceholderContent.ITEMS, this));
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        CronetEngine.Builder builder = new CronetEngine.Builder(this.requireContext());
        CronetEngine engine = builder.build();

        UrlRequest urlReq = engine.newUrlRequestBuilder("http://192.168.68.152:8080/place",
                        new ScenicSpotsReqCallback(
                                (jsonObject -> {
                                    try {
                                        JSONArray placesArray = jsonObject.getJSONArray("places");
                                        // Lặp qua mỗi đối tượng trong mảng places
                                        for (int i = 0; i < placesArray.length(); i++) {
                                            JSONObject placeObject = placesArray.getJSONObject(i);
                                            List<String> placeList = new ArrayList<>();
                                            // Lấy các trường thông tin của địa điểm
                                            int id = placeObject.getInt("id");
                                            String loc = placeObject.getString("loc");
                                            String province = placeObject.getString("province");
                                            String part = placeObject.getString("part");
                                            String summary = placeObject.getString("summary");
                                            String thumbUrl = placeObject.getString("thumbUrl");
                                            placeList.add(loc);
                                            placeList.add(province);
                                            placeList.add(part);
                                            placeList.add(summary);
                                            placeList.add(thumbUrl);

                                            PlaceholderContent.addItem(PlaceholderContent.createPlaceholderItem(id, placeList));
                                            Log.i("Giá trị placesArray", String.valueOf(placesArray));
                                        }
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }
                                })
                        ), Executors.newSingleThreadExecutor())
                .setHttpMethod("GET").build();

        urlReq.start();
    }
}