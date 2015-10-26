package qulix.com.giphytestapp.ui.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.ArrayList;

import qulix.com.giphytestapp.R;
import qulix.com.giphytestapp.data.GifDescription;

/**
 * Adapter to be used in gifs list
 */
public final class GifListAdapter extends RecyclerView.Adapter<GifPreviewViewHolder> {

    private final List<GifDescription> mDataSet = new ArrayList<GifDescription>();
    private final GifPreviewViewHolder.ClickListener mClickListener;

    public GifListAdapter(@NonNull final GifPreviewViewHolder.ClickListener clickListener) {

        mClickListener = clickListener;
    }

    @Override
    public GifPreviewViewHolder onCreateViewHolder(final ViewGroup parent,
                                                   final int viewType) {
        final View view
            = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.gif_preview_list_item,
                     parent,
                     false);

        return new GifPreviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GifPreviewViewHolder holder,
                                 final int position) {
        holder.bindGif(mDataSet.get(position),
                       mClickListener);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    // update data represented by this adapter
    public void updateData(final List<GifDescription> newData) {
        mDataSet.clear();
        mDataSet.addAll(newData);

        notifyDataSetChanged();
    }
}
