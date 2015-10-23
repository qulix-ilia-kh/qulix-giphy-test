package qulix.com.giphytestapp.ui.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import qulix.com.giphytestapp.R;
import qulix.com.giphytestapp.data.GifDescription;
import qulix.com.giphytestapp.ui.UiUtils;

public class GifPreviewViewHolder extends RecyclerView.ViewHolder {

    public interface ClickListener {
        void onClicked(GifDescription preview);
    }

    final SimpleDraweeView mImage;

    public GifPreviewViewHolder(final View itemView) {
        super(itemView);
        mImage = (SimpleDraweeView) itemView.findViewById(R.id.image);
    }

    public void bindGif(@NonNull final GifDescription gifPreview,
                        @NonNull final ClickListener clickListener) {
        mImage.setController(UiUtils.fromGifDescription(gifPreview));

        itemView.setOnClickListener(v -> clickListener.onClicked(gifPreview));
    }
}
