package com.asset.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InstrumentDto {
    public Date calibrationdate;
    public long userId;
    public String instrumentName;
    public String iIN;
}
