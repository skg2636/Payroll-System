/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grade;

/**
 *
 * @author KIIT
 */
public class Grade {
   
    private String gradeId, gradeName, hraPercentA,hraPercentB,hraPercentC, 
            taPercent,ltaPercent,daPercent,medicalAllowance, phonewifiAllowance,
            otherAllowance, profTax, pf ;

    public Grade(String gradeId, String gradeName, String hraPercentA, String hraPercentB, String hraPercentC, String taPercent, String ltaPercent, String daPercent, String medicalAllowance, String phonewifiAllowance, String otherAllowance, String profTax, String pf) {
        this.gradeId = gradeId;
        this.gradeName = gradeName;
        this.hraPercentA = hraPercentA;
        this.hraPercentB = hraPercentB;
        this.hraPercentC = hraPercentC;
        this.taPercent = taPercent;
        this.ltaPercent = ltaPercent;
        this.daPercent = daPercent;
        this.medicalAllowance = medicalAllowance;
        this.phonewifiAllowance = phonewifiAllowance;
        this.otherAllowance = otherAllowance;
        this.profTax = profTax;
        this.pf = pf;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getHraPercentA() {
        return hraPercentA;
    }

    public void setHraPercentA(String hraPercentA) {
        this.hraPercentA = hraPercentA;
    }

    public String getHraPercentB() {
        return hraPercentB;
    }

    public void setHraPercentB(String hraPercentB) {
        this.hraPercentB = hraPercentB;
    }

    public String getHraPercentC() {
        return hraPercentC;
    }

    public void setHraPercentC(String hraPercentC) {
        this.hraPercentC = hraPercentC;
    }

    public String getTaPercent() {
        return taPercent;
    }

    public void setTaPercent(String taPercent) {
        this.taPercent = taPercent;
    }

    public String getLtaPercent() {
        return ltaPercent;
    }

    public void setLtaPercent(String ltaPercent) {
        this.ltaPercent = ltaPercent;
    }

    public String getDaPercent() {
        return daPercent;
    }

    public void setDaPercent(String daPercent) {
        this.daPercent = daPercent;
    }

    public String getMedicalAllowance() {
        return medicalAllowance;
    }

    public void setMedicalAllowance(String medicalAllowance) {
        this.medicalAllowance = medicalAllowance;
    }

    public String getPhonewifiAllowance() {
        return phonewifiAllowance;
    }

    public void setPhonewifiAllowance(String phonewifiAllowance) {
        this.phonewifiAllowance = phonewifiAllowance;
    }

    public String getOtherAllowance() {
        return otherAllowance;
    }

    public void setOtherAllowance(String otherAllowance) {
        this.otherAllowance = otherAllowance;
    }

    public String getProfTax() {
        return profTax;
    }

    public void setProfTax(String profTax) {
        this.profTax = profTax;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }
    
    
    
}
