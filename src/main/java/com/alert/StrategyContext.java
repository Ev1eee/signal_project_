package com.alert;

import com.data_management.Patient;

public class StrategyContext {
    private AlertStrategy alertStrategy;

    public StrategyContext(AlertStrategy alertStrategy){
        this.alertStrategy=alertStrategy;
    }
    /**
     * @param patient the patient whose records are being evaluated
     * @return boolean
     */
    public boolean doStrategy(Patient patient){
        return alertStrategy.checkAlert(patient);
    }
}
