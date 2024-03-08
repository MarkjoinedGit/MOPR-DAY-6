package com.example.week6_nhom6_advertisingid.fragment;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.week6_nhom6_advertisingid.fragment.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.week6_nhom6_advertisingid.databinding.FragmentSceSpotsItemBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<PlaceholderItem> mValues;
    private Fragment mFragment;

    public MyItemRecyclerViewAdapter(List<PlaceholderItem> items, Fragment fragment) {
        mValues = items;
        mFragment = fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentSceSpotsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mLoc.setText(mValues.get(position).placeList.get(0));
        holder.mProvince.setText(mValues.get(position).placeList.get(1));
        holder.mPart.setText(mValues.get(position).placeList.get(2));
        holder.mSummary.setText(mValues.get(position).placeList.get(3));
        Log.i(this.getClass().getSimpleName(), (String) holder.mSummary.getText());
        String thumbUrl = "http://10.0.122.51:8080/" + mValues.get(position).placeList.get(4);
        Glide.with(mFragment)
                .load(thumbUrl)
                .into(holder.mThumbnail);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mLoc;
        public final TextView mProvince;
        public final TextView mPart;
        public final TextView mSummary;
        public PlaceholderItem mItem;
        public ImageView mThumbnail;

        public ViewHolder(FragmentSceSpotsItemBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mLoc = binding.loc;
            mProvince = binding.province;
            mSummary = binding.summary;
            mPart = binding.part;
            mThumbnail = binding.thumbnail;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mSummary.getText() + "'";
        }
    }
}