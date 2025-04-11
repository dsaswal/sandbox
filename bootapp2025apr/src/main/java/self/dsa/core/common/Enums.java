package self.dsa.core.common;

import jakarta.persistence.*;

public class Enums {
    public enum PartyType {
        INDIVIDUAL("Individual"),
        CORPORATE("Corporate"),
        COUNTERPARTY("Counterparty");

        private final String displayName;

        PartyType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    public enum TransactionStatus {
        PENDING("Pending"),
        COMPLETED("Completed"),
        FAILED("Failed");

        private final String displayName;

        TransactionStatus(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    public enum PartyStatus {
        ACTIVE("Active"),
        INACTIVE("Inactive");

        private final String displayName;

        PartyStatus(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
    // Add more enums here (hundreds if needed)
}