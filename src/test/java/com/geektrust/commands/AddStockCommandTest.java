package com.geektrust.commands;

import com.geektrust.PortFolio;
import com.geektrust.command.AddStockCommand;
import com.geektrust.mocks.DummyDataStore;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddStockCommandTest {
    @Test
    void execute() throws Exception {
        String command = "ADD_STOCK AXIS_BLUECHIP HDFC BANK LIMITED";
        DummyDataStore dataStore = new DummyDataStore();
        Set<String> axisBlueChipStocksBeforeUpdate = dataStore.getStocksFor("AXIS_BLUECHIP");
        assertEquals(Arrays.asList("INFOSYS LIMITED", "BHARTI AIRTEL LIMITED"), axisBlueChipStocksBeforeUpdate.stream().collect(Collectors.toList()));
        AddStockCommand addStockCommand = new AddStockCommand(dataStore);
        addStockCommand.execute(command, new PortFolio());
        Set<String> axisBlueChipStocks = dataStore.getStocksFor("AXIS_BLUECHIP");
        assertEquals(Arrays.asList("INFOSYS LIMITED", "BHARTI AIRTEL LIMITED", "HDFC BANK LIMITED"), axisBlueChipStocks.stream().collect(Collectors.toList()));
    }
}
