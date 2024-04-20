public class EmployeeModel {
    public int id;
    public String name;

    public int jobId;

    public EmployeeModel() {
        id = -1;
        name = "";
        jobId = -1;
    }

    public EmployeeModel(int id, String name, int jobId) {
        this.id = id;
        this.name = name;
        this.jobId = jobId;
    }


}
