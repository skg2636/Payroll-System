
package Grade;

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
    
    
    public boolean checkSimliarGradeId(Grade anotherGrade){
        return this.gradeId.equals(anotherGrade.getGradeId());
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

    @Override
    public boolean equals(Object obj) {
       if(this == obj)
           return true;
       
       if(obj == null || obj.getClass() != this.getClass())
           return false;
       
       Grade another = (Grade) obj;
       
       return this.gradeName.equals(another.gradeName) &&
              this.hraPercentA.equals(another.hraPercentA) &&
              this.hraPercentB.equals(another.hraPercentB) &&
              this.hraPercentC.equals(another.hraPercentC) &&
              this.taPercent.equals(another.taPercent) &&
               this.ltaPercent.equals(another.ltaPercent) &&
               this.daPercent.equals(another.daPercent) &&
               this.medicalAllowance.equals(another.medicalAllowance) &&
               this.phonewifiAllowance.equals(another.phonewifiAllowance) &&
               this.otherAllowance.equals(another.otherAllowance) &&
               this.profTax.equals(another.profTax) &&
               this.pf.equals(another.pf) ;
              
    }
    
    
    
    
    
    
}
