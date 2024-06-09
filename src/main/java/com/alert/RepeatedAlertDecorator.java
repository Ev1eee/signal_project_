package com.alert;

/*
 * RepeatedAlertDecorator
 */
    public class RepeatedAlertDecorator implements AlertDecorator {
        Alert alert;

        public RepeatedAlertDecorator(Alert alert){
            this.alert=alert;
        }
        @Override
        /**
         * @return boolean
         */
        public boolean alert() {
            System.out.println(alert.toString());
            return true;
        }
}
