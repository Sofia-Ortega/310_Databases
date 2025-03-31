public class JobModel {
    public int id;

    public String title;

    public double wage;

    public JobModel(int id, String title, double wage) {
        this.id = id;
        this.title = title;
        this.wage = wage;
    }

    public JobModel() {
        this.id = -1;
        this.title = "";
        this.wage = 0.00;
    }
}
