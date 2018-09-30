package net.knightech.shopper.domain;

import java.util.List;

public class LineItemList {

    private List<LineItem> lineItemList;

    public LineItemList(List<LineItem> lineItemList) {
        this.lineItemList = lineItemList;
    }

    public List<LineItem> getLineItemList() {
        return lineItemList;
    }

    public LineItemList() {
    }

    public void setLineItemList(List<LineItem> lineItemList) {
        this.lineItemList = lineItemList;
    }
}
