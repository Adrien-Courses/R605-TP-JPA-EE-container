package fr.adriencaubel.controller;

import java.util.List;

public class PaginatedResponse<T> {
    private long total;
    private int page;
    private int size;
    private List<T> items;

    public PaginatedResponse(int page, int size, long total, List<T> items) {
        this.total = total;
        this.page = page;
        this.size = size;
        this.items = items;
    }

    public long getTotal() { return total; }
    public int getPage() { return page; }
    public int getSize() { return size; }
    public List<T> getItems() { return items; }
}