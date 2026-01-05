package com.example.car_sharing_backend.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DrivingLicenseRequestStatus {

        PENDING("PENDING"),
        APPROVED("APPROVED"),
        REJECTED("REJECTED");

        private final String value;

        @JsonValue
        @Override
        public String toString() {
                return value;
        }

        @JsonCreator
        public static DrivingLicenseRequestStatus fromValue(String value) {
                for (DrivingLicenseRequestStatus status : DrivingLicenseRequestStatus.values()) {
                        if (status.value.equalsIgnoreCase(value)) {
                                return status;
                        }
                }
                throw new IllegalArgumentException("Неизвестный статус заявки: " + value);
        }
}
