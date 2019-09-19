package cn.sunxn.service;

import java.util.Date;

public interface TimeService {

    /**
     * 格式化 日期 ---> 文本
     * @param date
     * @return
     */
    String getFormat(Date date);

    /**
     * 解析 文本 ---> 日期
     * @param str
     * @return
     */
    Date getParse(String str);
}
