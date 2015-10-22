package qulix.com.giphytestapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class GifListAdapter extends RecyclerView.Adapter<GifPreviewViewHolder> {

    private List<GifPreview> dataSet;

    public GifListAdapter(List<GifPreview> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public GifPreviewViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.gif_preview_list_item, parent, false);
        return new GifPreviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GifPreviewViewHolder holder, final int position) {
        holder.bindGif(dataSet.get(position), position);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
