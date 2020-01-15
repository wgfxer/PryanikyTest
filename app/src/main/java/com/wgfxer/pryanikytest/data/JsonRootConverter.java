package com.wgfxer.pryanikytest.data;

import com.wgfxer.pryanikytest.data.models.list.BaseListItem;
import com.wgfxer.pryanikytest.data.models.web.DataItem;
import com.wgfxer.pryanikytest.data.models.web.JsonRoot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Конвертер JsonRoot объекта в готовый список BaseListItem для списка
 */
public class JsonRootConverter {
    private DataItemConverter dataItemConverter;

    public JsonRootConverter() {
        dataItemConverter = new DataItemConverter();
    }

    public List<BaseListItem> convert(JsonRoot jsonRoot) {
        List<String> viewInOrder = jsonRoot.getViewList();
        List<DataItem> dataItemList = jsonRoot.getDataList();
        Map<String, BaseListItem> mapBaseItems = new HashMap<>();
        for (DataItem dataItem : dataItemList) {
            mapBaseItems.put(dataItem.getName(), dataItemConverter.convert(dataItem));
        }
        List<BaseListItem> resultList = new ArrayList<>();
        for (String view : viewInOrder) {
            resultList.add(mapBaseItems.get(view));
        }
        return resultList;
    }

}
