public class AssignmentModel {

    public EmployeeModel employee;

    public double wage;
    double hoursBilled;

    public AssignmentModel(EmployeeModel e, double wage, double h) {
        this.employee = e;
        this.wage = wage;
        this.hoursBilled = h;
    }
}
