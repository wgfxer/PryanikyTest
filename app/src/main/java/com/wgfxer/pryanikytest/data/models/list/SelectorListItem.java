package com.wgfxer.pryanikytest.data.models.list;

import com.wgfxer.pryanikytest.R;
import com.wgfxer.pryanikytest.data.models.web.Variant;

import java.util.List;

/**
 * Элемент списка с селектором
 */
public class SelectorListItem extends BaseListItem {
    private int selectedId;
    private List<Variant> variants;

    public SelectorListItem() {
        setLayoutId(R.layout.selector_list_item);
    }

    public int getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }
}
