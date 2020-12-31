package com.example.bustracking;

public class busLocationDetails {
    Double latitude;
    Double logitude;

    public busLocationDetails(Double lat, Double log) {
    this.latitude=lat;
    this.logitude=log;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLogitude() {
        return logitude;
    }

    public void setLogitude(Double logitude) {
        this.logitude = logitude;
    }
}
