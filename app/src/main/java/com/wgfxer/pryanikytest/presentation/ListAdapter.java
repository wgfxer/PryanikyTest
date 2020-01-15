package com.wgfxer.pryanikytest.presentation;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wgfxer.pryanikytest.R;
import com.wgfxer.pryanikytest.data.models.list.BaseListItem;
import com.wgfxer.pryanikytest.data.models.list.ImageListItem;
import com.wgfxer.pryanikytest.data.models.list.SelectorListItem;
import com.wgfxer.pryanikytest.data.models.list.TextListItem;
import com.wgfxer.pryanikytest.data.models.web.Variant;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.BaseViewHolder> {
    private List<BaseListItem> listItems;
    private OnItemClickListener onItemClickListener;
    private OnRadioButtonClickListener onRbClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnRbClickListener(OnRadioButtonClickListener onRbClickListener) {
        this.onRbClickListener = onRbClickListener;
    }

    public void setListItems(List<BaseListItem> listItems) {
        this.listItems = listItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflateByViewType(parent, viewType);
        switch (viewType) {
            case R.layout.image_list_item: {
                return new ImageItemHolder(view);
            }
            case R.layout.selector_list_item: {
                return new SelectorItemHolder(view);
            }
            case R.layout.text_list_item: {
                return new TextItemHolder(view);
            }
            default:
                throw new IllegalStateException("There is no match with current layoutId");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(listItems.get(position));
    }

    @Override
    public int getItemCount() {
        return listItems == null ? 0 : listItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return listItems.get(position).getLayoutId();
    }

    class ImageItemHolder extends BaseViewHolder {

        private ImageView imageView;
        private TextView textView;

        @SuppressLint("CutPasteId")
        ImageItemHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.text_view);
        }

        @Override
        void bind(BaseListItem listItem) {
            super.bind(listItem);
            if (listItem instanceof ImageListItem) {
                ImageListItem imageListItem = (ImageListItem) listItem;
                Picasso.get().load(imageListItem.getUrl()).into(imageView);
                textView.setText(imageListItem.getText());
                itemView.setOnClickListener(v -> {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(listItem.getName());
                    }
                });
            } else {
                throw new IllegalStateException("Incompatible types of list item and holder");
            }
        }
    }

    class SelectorItemHolder extends BaseViewHolder {

        private RadioGroup radioGroup;

        SelectorItemHolder(@NonNull View itemView) {
            super(itemView);
            radioGroup = itemView.findViewById(R.id.radio_group);
        }

        @Override
        void bind(BaseListItem listItem) {
            super.bind(listItem);
            if (listItem instanceof SelectorListItem) {
                radioGroup.setOnCheckedChangeListener(null);
                SelectorListItem selectorListItem = (SelectorListItem) listItem;
                radioGroup.removeAllViews();
                for (Variant variant : selectorListItem.getVariants()) {
                    RadioButton button = new RadioButton(itemView.getContext());
                    button.setText(variant.getText());
                    radioGroup.addView(button);
                    if (variant.getId() == selectorListItem.getSelectedId()) {
                        button.setChecked(true);
                    }
                }
                radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                    if (onRbClickListener != null) {
                        onRbClickListener.onRbClick(group.indexOfChild(group.findViewById(checkedId)));
                    }
                });
            } else {
                throw new IllegalStateException("Incompatible types of list item and holder");
            }
        }
    }

    class TextItemHolder extends BaseViewHolder {

        private TextView textView;

        TextItemHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
        }

        @Override
        void bind(BaseListItem listItem) {
            super.bind(listItem);
            if (listItem instanceof TextListItem) {
                TextListItem textListItem = (TextListItem) listItem;
                textView.setText(textListItem.getText());
            } else {
                throw new IllegalStateException("Incompatible types of list item and holder");
            }
        }
    }

    abstract class BaseViewHolder extends RecyclerView.ViewHolder {

        BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(BaseListItem listItem) {
            itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(listItem.getName());
                }
            });
        }

        ;
    }

    private View inflateByViewType(@NonNull ViewGroup parent, int layoutResId) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);
    }

    interface OnItemClickListener {
        void onItemClick(String itemName);
    }

    interface OnRadioButtonClickListener {
        void onRbClick(int rbId);
    }
}
