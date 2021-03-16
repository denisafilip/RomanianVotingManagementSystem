package Model.election.enums;

public enum Question {
    QUESTION1("Sunteți de acord cu interzicerea amnistiei și grațierii pentru infracțiuni de corupție?"),
    QUESTION2(" Sunteți de acord cu interzicerea adoptării de către Guvern a ordonanțelor de urgență în domeniul infracțiunilor, pedepselor și al organizării judiciare și cu extinderea dreptului de a ataca ordonanțele direct la Curtea Constituțională?");

    private final String label;

    Question(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
