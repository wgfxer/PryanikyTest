package com.wgfxer.pryanikytest.data;

import com.wgfxer.pryanikytest.data.models.list.BaseListItem;
import com.wgfxer.pryanikytest.data.models.list.ImageListItem;
import com.wgfxer.pryanikytest.data.models.list.SelectorListItem;
import com.wgfxer.pryanikytest.data.models.list.TextListItem;
import com.wgfxer.pryanikytest.data.models.web.Data;
import com.wgfxer.pryanikytest.data.models.web.DataItem;

/**
 * Конвертер DataItem в BaseListItem(базовый элемент списка)
 */
public class DataItemConverter {
    public BaseListItem convert(DataItem item) {
        Data data = item.getData();
        if (data.getUrl() != null && data.getText() != null) {
            ImageListItem listItem = new ImageListItem();
            listItem.setText(data.getText());
            listItem.setUrl(data.getUrl());
            listItem.setName(item.getName());
            return listItem;
        } else if (data.getSelectedId() != null && data.getVariants() != null) {
            SelectorListItem listItem = new SelectorListItem();
            listItem.setSelectedId(data.getSelectedId());
            listItem.setVariants(data.getVariants());
            listItem.setName(item.getName());
            return listItem;
        } else if (data.getText() != null) {
            TextListItem listItem = new TextListItem();
            listItem.setText(data.getText());
            listItem.setName(item.getName());
            return listItem;
        } else {
            throw new IllegalStateException("This type of data not yet implemented");
        }
    }
}
