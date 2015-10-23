package qulix.com.giphytestapp.ui.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import qulix.com.giphytestapp.R;
import qulix.com.giphytestapp.data.GifDescription;

public class GifListAdapter extends RecyclerView.Adapter<GifPreviewViewHolder> {

    private List<GifDescription> mDataSet;
    private final GifPreviewViewHolder.ClickListener mClickListener;

    public GifListAdapter(
            @NonNull final List<GifDescription> dataSet,
            @NonNull final GifPreviewViewHolder.ClickListener clickListener) {
        mDataSet = dataSet;
        mClickListener = clickListener;
    }

    @Override
    public GifPreviewViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.gif_preview_list_item, parent, false);
        return new GifPreviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GifPreviewViewHolder holder, final int position) {
        holder.bindGif(mDataSet.get(position), position, mClickListener);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
