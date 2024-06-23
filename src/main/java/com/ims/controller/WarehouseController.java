package com.ims.controller;

import com.ims.strategy.WarehouseSelectionStrategy;
import com.ims.warehouse.Warehouse;

import java.util.List;

public class WarehouseController {
    private List<Warehouse> warehouseList;
    private WarehouseSelectionStrategy warehouseSelectionStrategy;

    public WarehouseController(List<Warehouse> warehouseList, WarehouseSelectionStrategy warehouseSelectionStrategy){
        this.warehouseList = warehouseList;
        this.warehouseSelectionStrategy = warehouseSelectionStrategy;
    }

    public void addNewWarehouse(Warehouse warehouse){
        warehouseList.add(warehouse);
    }

    public void removeWarehouse(Warehouse warehouse){
        warehouseList.remove(warehouse);
    }

    public Warehouse selectWarehouse(){
        return warehouseSelectionStrategy.selectWarehouse(warehouseList);
    }
}
