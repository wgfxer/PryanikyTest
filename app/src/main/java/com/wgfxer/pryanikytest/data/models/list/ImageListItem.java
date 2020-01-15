package com.wgfxer.pryanikytest.data.models.list;

import com.wgfxer.pryanikytest.R;
import com.wgfxer.pryanikytest.data.models.list.BaseListItem;

/**
 * Элемент списка с картинкой
 */
public class ImageListItem extends BaseListItem {
    private String url;
    private String text;

    public ImageListItem() {
        setLayoutId(R.layout.image_list_item);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
