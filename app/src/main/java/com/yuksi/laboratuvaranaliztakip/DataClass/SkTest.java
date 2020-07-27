package com.yuksi.laboratuvaranaliztakip.DataClass;

public class SkTest {
    private int skTestId;
    private String testAdi;
    private String testDurum;

    public SkTest(int testId, String testAdi, String testDurum) {
        this.skTestId = testId;
        this.testAdi = testAdi;
        this.testDurum = testDurum;
    }

    public int getTestId() {
        return skTestId;
    }

    public void setTestId(int testId) {
        this.skTestId = testId;
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
