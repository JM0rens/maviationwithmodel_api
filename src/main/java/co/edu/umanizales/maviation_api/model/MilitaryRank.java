package co.edu.umanizales.maviation_api.model;

/**
 * Enum representing military ranks
 */
public enum MilitaryRank {
    AIRMAN_BASIC("E-1", "Airman Basic"),
    AIRMAN("E-2", "Airman"),
    AIRMAN_FIRST_CLASS("E-3", "Airman First Class"),
    SENIOR_AIRMAN("E-4", "Senior Airman"),
    STAFF_SERGEANT("E-5", "Staff Sergeant"),
    TECHNICAL_SERGEANT("E-6", "Technical Sergeant"),
    MASTER_SERGEANT("E-7", "Master Sergeant"),
    SENIOR_MASTER_SERGEANT("E-8", "Senior Master Sergeant"),
    CHIEF_MASTER_SERGEANT("E-9", "Chief Master Sergeant"),
    SECOND_LIEUTENANT("O-1", "Second Lieutenant"),
    FIRST_LIEUTENANT("O-2", "First Lieutenant"),
    CAPTAIN("O-3", "Captain"),
    MAJOR("O-4", "Major"),
    LIEUTENANT_COLONEL("O-5", "Lieutenant Colonel"),
    COLONEL("O-6", "Colonel"),
    BRIGADIER_GENERAL("O-7", "Brigadier General"),
    MAJOR_GENERAL("O-8", "Major General"),
    LIEUTENANT_GENERAL("O-9", "Lieutenant General"),
    GENERAL("O-10", "General");

    private final String payGrade;
    private final String title;

    MilitaryRank(String payGrade, String title) {
        this.payGrade = payGrade;
        this.title = title;
    }

    public String getPayGrade() {
        return payGrade;
    }

    public String getTitle() {
        return title;
    }
}
