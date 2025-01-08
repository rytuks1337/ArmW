package com.rytis.armw.ui.queue_controller;

public interface RefreshListener {
    void onRefreshToggle(boolean isRefreshing);

    void forceRefresh();
}
