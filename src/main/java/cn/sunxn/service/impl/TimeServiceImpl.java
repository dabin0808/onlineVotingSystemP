package cn.sunxn.service.impl;

import cn.sunxn.service.TimeService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServiceImpl implements TimeService {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    public String getFormat(Date date) {
        return sdf.format(date);
    }

    @Override
    public Date getParse(String str) {
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
