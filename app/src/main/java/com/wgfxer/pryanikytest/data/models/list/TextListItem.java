package com.wgfxer.pryanikytest.data.models.list;

import com.wgfxer.pryanikytest.R;
import com.wgfxer.pryanikytest.data.models.list.BaseListItem;

/**
 * Элемент списка с текстом
 */
public class TextListItem extends BaseListItem {
    private String text;

    public TextListItem() {
        setLayoutId(R.layout.text_list_item);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
