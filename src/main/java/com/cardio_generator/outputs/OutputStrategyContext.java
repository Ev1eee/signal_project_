package com.cardio_generator.outputs;

public class OutputStrategyContext {
    private OutputStrategy outputStrategy;
    public OutputStrategyContext(OutputStrategy outputStrategy){
        this.outputStrategy=outputStrategy;
    }
    public void doStrategy(int patientId, long timestamp, String label, String data){
        outputStrategy.output(patientId,timestamp,label,data);
    }
}
