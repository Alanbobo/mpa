package com.commandcenter.model.ppcs;

/**
 * @Author
 *  2019/1/18.
 */
public class DutyMonth {
    /**
     * 日期 yyyy-mm-dd
     */
    private String dutyDay;
    /**
     * 主班是否有排班 有Y 没有N
     */
    private String mainClass;
    /**
     * 辅班是否有排班 有Y 没有N
     */
    private String auxiliaryClass;

    public String getDutyDay() {
        return dutyDay;
    }

    public void setDutyDay(String dutyDay) {
        this.dutyDay = dutyDay;
    }

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public String getAuxiliaryClass() {
        return auxiliaryClass;
    }

    public void setAuxiliaryClass(String auxiliaryClass) {
        this.auxiliaryClass = auxiliaryClass;
    }
}
