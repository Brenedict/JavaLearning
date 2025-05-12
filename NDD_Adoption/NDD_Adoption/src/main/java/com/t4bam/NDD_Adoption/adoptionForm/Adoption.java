package com.t4bam.NDD_Adoption.adoptionForm;

public class Adoption {
    private String adopterId;
    private String adopterName;
    private int adopterAge;
    private String employmentStatus;

    public Adoption() {}

    public Adoption(String adopterId, String adopterName, int adopterAge, String employmentStatus) {
        this.adopterId = adopterId;
        this.adopterName = adopterName;
        this.adopterAge = adopterAge;
        this.employmentStatus = employmentStatus;
    }

    public Adoption(String adopterName, int adopterAge, String employmentStatus) {
        this.adopterName = adopterName;
        this.adopterAge = adopterAge;
        this.employmentStatus = employmentStatus;
    }

    public void setAdopterId(String adopterId) {
        this.adopterId = adopterId;
    }

    public String getAdopterId() {
        return adopterId;
    }

    public void setAdopterName(String adopterName) {
        this.adopterName = adopterName;
    }

    public String getAdopterName() {
        return adopterName;
    }

    public void setAdopterAge(int adopterAge) {
        this.adopterAge = adopterAge;
    }

    public int getAdopterAge() {
        return adopterAge;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

}
