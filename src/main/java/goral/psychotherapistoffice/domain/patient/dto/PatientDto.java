package goral.psychotherapistoffice.domain.patient.dto;

public class PatientDto {
    private Long id;
    private String nick;
    private String name;
    private String surname;
    private String email;
    private String telephone;
    private int yearOfBrith;
    private boolean  approval;
    private String  information;

    public PatientDto() {

    }

    public PatientDto(Long id, String nick, String name, String surname, String email, String telephone, int yearOfBrith, boolean approval, String  information) {
        this.id = id;
        this.nick = nick;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.telephone = telephone;
        this.yearOfBrith = yearOfBrith;
        this.approval = isApproval();
        this.information = information;
    }

    public PatientDto(Long id, String nick, String name, String surname, String email, String telephone, int yearOfBrith, String information, boolean approval) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getYearOfBrith() {
        return yearOfBrith;
    }

    public void setYearOfBrith(int yearOfBrith) {
        this.yearOfBrith = yearOfBrith;
    }

    public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
