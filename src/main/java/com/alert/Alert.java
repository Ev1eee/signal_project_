package com.alert;

// Represents an alert
public class Alert {
    private String patientId;
    private String condition;
    private long timestamp;
    private String type;

    public Alert(String patientId, String condition, long timestamp) {
        this.patientId = patientId;
        this.condition = condition;
        this.timestamp = timestamp;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getCondition() {
        return condition;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    /**
     * Manually copies an Alert object.
     *
     * @return A new Alert object that is a copy of the current instance.
     */

     public Alert copy() {
         Alert copy = new Alert(this.patientId, this.condition, this.timestamp);
         copy.setType(this.type);
         return copy;
     }

    /**
     * Creates a repeated alert by copying the given alert.
     *
     * @param alert The alert object to be copied
     * @return A new Alert object that is a copy of the provided alert
     */
    public Alert RepeatedAlertDecorator(Alert alert) {
        return alert.copy();
    }
}


