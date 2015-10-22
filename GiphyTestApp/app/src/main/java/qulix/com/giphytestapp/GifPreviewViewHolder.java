package qulix.com.giphytestapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class GifPreviewViewHolder extends RecyclerView.ViewHolder {

    public interface ClickListener {
        void onGifPreviewClicked(GifPreview preview);
    }

    private final TextView mPositionIndex;

    public GifPreviewViewHolder(final View itemView) {
        super(itemView);
        mPositionIndex = (TextView) itemView.findViewById(R.id.position_index);
    }

    public void bindGif(
            @NonNull final GifPreview gifPreview,
            final int position,
            @NonNull final ClickListener clickListener) {
        mPositionIndex.setText(String.format("%d)", position));

        itemView.setOnClickListener(v -> clickListener.onGifPreviewClicked(gifPreview));
    }
}
