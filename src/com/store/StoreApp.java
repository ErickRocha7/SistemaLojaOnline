package com.store;

import com.store.controller.StoreController;

public class StoreApp {
    public static void main(String[] args) {
        StoreController controller = new StoreController();
        controller.run();
    }
}