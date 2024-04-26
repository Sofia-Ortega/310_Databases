import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectModel {
    public int id;
    public String projectName;
    public double totalCharge;
    public int projectLeaderID;

    public List<AssignmentModel> assignments;

    public ProjectModel() {
        this.id = -1;
        this.projectName = "";
        this.totalCharge = 0.0;
        this.projectLeaderID = -1;
        this.assignments = new ArrayList<>();
    }

    public void addAssignment(EmployeeModel employee, double wage, double hoursBilled) {
        AssignmentModel assignment = new AssignmentModel(employee, wage, hoursBilled);
        assignments.add(assignment);
    }

    public String[][] getTableData() {
        int numRows = assignments.size();
        String[][] tableData = new String[numRows][3]; // 3 columns: Employee, HoursBilled, TotalWage

        for (int i = 0; i < numRows; i++) {
            AssignmentModel assignment = assignments.get(i);
            String employeeName = assignment.employee.name;
            double hoursBilled = assignment.hoursBilled;
            double totalWage = hoursBilled * assignment.wage;

            tableData[i][0] = employeeName;
            tableData[i][1] = String.valueOf(hoursBilled);
            tableData[i][2] = String.valueOf(totalWage);
        }

        return tableData;
    }

}
