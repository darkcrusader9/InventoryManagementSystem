package com.ims.strategy;

import com.ims.warehouse.Warehouse;

import java.util.List;

public class NearestWarehouseSelectionStrategy implements WarehouseSelectionStrategy {
    @Override
    public Warehouse selectWarehouse(List<Warehouse> warehouseList) {
        return warehouseList.get(0);
    }
}
