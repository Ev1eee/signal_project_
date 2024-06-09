package com.alert;

/**
 * the functionality to clone an Alert object
 */
    public class RepeatedAlertDecorator implements AlertDecorator {
        Alert alert;
        public RepeatedAlertDecorator(Alert alert){
            this.alert=alert;
        }
        @Override
        public boolean alert() {
            System.out.println(alert.toString());
            return true;
        }
}
