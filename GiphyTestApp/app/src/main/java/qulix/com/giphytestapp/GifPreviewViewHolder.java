package qulix.com.giphytestapp;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class GifPreviewViewHolder extends RecyclerView.ViewHolder {

    public interface ClickListener {
        void onGifPreviewClicked(GifPreview preview);
    }

    private final TextView mPositionIndex;

    public GifPreviewViewHolder(final View itemView) {
        super(itemView);
        mPositionIndex = (TextView) itemView.findViewById(R.id.position_index);
    }

    public void bindGif(@NonNull final GifPreview gifPreview,
                        final int position,
                        @NonNull final ClickListener clickListener) {
        mPositionIndex.setText(String.format("%d)", position));

        initImage(itemView, gifPreview);

        itemView.setOnClickListener(v -> clickListener.onGifPreviewClicked(gifPreview));
    }

    private void initImage(final View item,
                           final GifPreview gif) {
        final SimpleDraweeView image = (SimpleDraweeView)itemView.findViewById(R.id.image);

        final Uri uri = Uri.parse(gif.previewUrl());

        final DraweeController controller = Fresco.newDraweeControllerBuilder()
            .setUri(uri)
            .setAutoPlayAnimations(true)
            .build();

        image.setController(controller);
    }
}
