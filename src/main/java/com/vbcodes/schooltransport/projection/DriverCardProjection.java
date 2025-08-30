package com.vbcodes.schooltransport.projection;

public interface DriverCardProjection {
    public Long getTotalDrivers();
    public Long getUnmappedDrivers();
    public Long getDriversInCommute();
}
