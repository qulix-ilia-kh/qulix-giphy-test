package qulix.com.giphytestapp.ui.list;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import qulix.com.giphytestapp.R;
import qulix.com.giphytestapp.data.GifDescription;
import qulix.com.giphytestapp.ui.UiUtils;

public class GifPreviewViewHolder extends RecyclerView.ViewHolder {

    public interface ClickListener {
        void onClicked(GifDescription preview);
    }

    private final TextView mPositionIndex;

    public GifPreviewViewHolder(final View itemView) {
        super(itemView);
        mPositionIndex = (TextView) itemView.findViewById(R.id.position_index);
    }

    public void bindGif(@NonNull final GifDescription gifPreview,
                        final int position,
                        @NonNull final ClickListener clickListener) {
        mPositionIndex.setText(String.format("%d)", position));

        initImage(itemView, gifPreview);

        itemView.setOnClickListener(v -> clickListener.onClicked(gifPreview));
    }

    private void initImage(final View item,
                           final GifDescription gif) {
        final SimpleDraweeView image = (SimpleDraweeView)itemView.findViewById(R.id.image);

        image.setController(UiUtils.fromGifDescription(gif));
    }
}
