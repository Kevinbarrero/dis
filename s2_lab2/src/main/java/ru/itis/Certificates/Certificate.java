package ru.itis.Certificates;

public class Certificate {
    private String nameKey;
    private String nameCert;
    private String country;
    private String state;
    private String city;
    private String organization;
    private String section;
    private String email;
    public Certificate(String nameKey, String nameCert, String country, String state, String city, String organization, String section, String email) {
        this.nameKey = nameKey;
        this.nameCert = nameCert;
        this.country = country;
        this.state = state;
        this.city = city;
        this.organization = organization;
        this.section = section;
        this.email = email;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public String subjParams(){
        return "-subj /C=" + this.getCountry() + "/ST=" + this.getState() + "/L=" + this.getCity() + "/O=" + this.getOrganization() + "/OU=" + this.getSection() + "/CN=" + this.getEmail();
    }

    public void setNameCert(String nameCert) {
        this.nameCert = nameCert;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameKey() {
        return nameKey;
    }

    public String getNameCert() {
        return nameCert;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getOrganization() {
        return organization;
    }

    public String getSection() {
        return section;
    }
    public String getEmail() {
        return email;
    }
}
