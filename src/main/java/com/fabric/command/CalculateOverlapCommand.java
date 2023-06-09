package com.fabric.command;

import com.fabric.DataStore;
import com.fabric.PortFolio;
import com.fabric.Printer;
import com.fabric.exception.FundNotFoundException;
import com.fabric.utils.FundOverlapCalculator;

import java.util.Set;

public class CalculateOverlapCommand implements Command {
    private PortFolio portFolio;
    private DataStore dataStore;
    private Printer printer;

    public CalculateOverlapCommand(PortFolio portFolio, DataStore dataStore, Printer printer) {
        this.portFolio = portFolio;
        this.dataStore = dataStore;
        this.printer = printer;
    }

    public void execute(String command) {
        String fund = command.split(" ")[1];
        try {
            Set<String> requestedFundStocks = dataStore.getStocksFor(fund);
            for (String currentFund : this.portFolio.getFunds()) {
                Set<String> currentFundStock = dataStore.getStocksFor(currentFund);
                String overlapPercent = new FundOverlapCalculator().calculate(currentFundStock, requestedFundStocks);
                if (Double.parseDouble(overlapPercent) > (double) 0) {
                    printer.print(fund + " " + currentFund + " " + overlapPercent + "%");
                }
            }

        } catch (FundNotFoundException exception) {
            printer.print(exception.getMessage());
        }
    }
}
