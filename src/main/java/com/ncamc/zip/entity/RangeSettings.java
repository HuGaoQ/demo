package com.ncamc.zip.entity;

import lombok.Data;

@Data
public class RangeSettings {
    private long start;
    private long end;
    private long contentLength;
    private long totalLength;

    public RangeSettings() {
    }

    public RangeSettings(long start, long end, long contentLength, long totalLength) {
        this.start = start;
        this.end = end;
        this.contentLength = contentLength;
        this.totalLength = totalLength;
    }

    public boolean isRange() {
        return this.start > 0L || this.contentLength < this.totalLength;
    }
}
