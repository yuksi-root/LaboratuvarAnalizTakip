package com.yuksi.laboratuvaranaliztakip.DataClass;

public class TumTest {
    private int testId;
    private String testAdi;
    private String testDurum;

    public TumTest(int testId, String testAdi, String testDurum) {
        this.testId = testId;
        this.testAdi = testAdi;
        this.testDurum = testDurum;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getTestAdi() {
        return testAdi;
    }

    public void setTestAdi(String testAdi) {
        this.testAdi = testAdi;
    }

    public String getTestDurum() {
        return testDurum;
    }

    public void setTestDurum(String testDurum) {
        this.testDurum = testDurum;
    }
}
