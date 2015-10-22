package qulix.com.giphytestapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class GifPreviewViewHolder extends RecyclerView.ViewHolder {

    private final TextView mPositionIndex;

    public GifPreviewViewHolder(final View itemView) {
        super(itemView);
        mPositionIndex = (TextView) itemView.findViewById(R.id.position_index);
    }

    public void bindGif(final GifPreview gifPreview, final int position) {
        mPositionIndex.setText(String.format("%d) %s", position, gifPreview.previewName()));
    }
}
