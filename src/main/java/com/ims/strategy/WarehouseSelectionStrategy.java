package com.ims.strategy;

import com.ims.warehouse.Warehouse;

import java.util.List;

public interface WarehouseSelectionStrategy {
    public Warehouse selectWarehouse(List<Warehouse> warehouseList);
}
