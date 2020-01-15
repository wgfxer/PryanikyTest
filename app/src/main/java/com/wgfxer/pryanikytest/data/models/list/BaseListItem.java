package com.wgfxer.pryanikytest.data.models.list;

/**
 * Базовый класс для всех элементов списка
 */
public abstract class BaseListItem {
    protected String name;
    protected int layoutId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }
}
