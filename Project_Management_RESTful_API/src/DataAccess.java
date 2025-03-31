
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DataAccess {

    Connection connection;

    Statement statement;

    public void connect() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password");
            this.statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean createJobClass(JobModel job) {

        String insertQuery = "INSERT INTO JobClass (id, title, wage) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setInt(1, job.id);
            preparedStatement.setString(2, job.title);
            preparedStatement.setDouble(3, job.wage);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Job inserted successfully");
            }

            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }


    public JobModel readJobClass(int id) {
        JobModel j = new JobModel();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM JobClass WHERE id=" + id);
            while(resultSet.next()) {
                j.id = resultSet.getInt("ID");
                j.title = resultSet.getString("title");
                j.wage = resultSet.getDouble("wage");
            }

            return j;
        } catch(Exception e) {
            e.printStackTrace();
        }

        return j;
    }



    public boolean updateJobClass(JobModel job) {
        String query = "UPDATE JobClass SET title = ?, wage = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, job.title);
            preparedStatement.setDouble(2, job.wage);
            preparedStatement.setInt(3, job.id);

            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0) {
                System.out.println(rowsAffected + " row updated with id " + job.id);
                return true;
            } else {
                System.out.println("No rows with id " + job.id + " found");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    public boolean deleteJobClass(int id) {
        String deleteQuery = "DELETE FROM JobClass WHERE id=" + id;

        try {
            int rowsAffected = statement.executeUpdate(deleteQuery);
            if(rowsAffected > 0) {
                System.out.println(rowsAffected + " rows with id " + id + " deleted");
            } else {
                System.out.println("No row with id " + id + " found");
            }

            return true;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean createEmployee(EmployeeModel employee) {
        String insertQuery = "INSERT INTO Employee (id, employeename, jobclassid) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setInt(1, employee.id);
            preparedStatement.setString(2, employee.name);
            preparedStatement.setInt(3, employee.jobId);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Employee inserted successfully");
            }

            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public EmployeeModel readEmployee(int id) {
        EmployeeModel employee = new EmployeeModel();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Employee WHERE id=" + id);
            while(resultSet.next()) {
                employee.id = resultSet.getInt("id");
                employee.name = resultSet.getString("employeename");
                employee.jobId = resultSet.getInt("jobclassid");
            }

            return employee;
        } catch(Exception e) {
            e.printStackTrace();
        }

        return employee;
    }

    public List<EmployeeModel> readEmployees() {
        List<EmployeeModel> employeeList = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Employee");

            while (resultSet.next()) {
                EmployeeModel employee = new EmployeeModel();
                employee.id = resultSet.getInt("id");
                employee.name = resultSet.getString("employeename");
                employee.jobId = resultSet.getInt("jobclassid");
                employeeList.add(employee);
            }

            return employeeList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return employeeList;
    }

    public boolean updateEmployee(EmployeeModel employee) {
        String query = "UPDATE Employee SET employeename = ?, jobclassid = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, employee.name);
            preparedStatement.setInt(2, employee.jobId);
            preparedStatement.setInt(3, employee.id);

            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0) {
                System.out.println(rowsAffected + " row updated with ID " + employee.id);
                return true;
            } else {
                System.out.println("No rows with ID " + employee.id + " found");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteEmployee(int id) {
        String deleteQuery = "DELETE FROM Employee WHERE id=" + id;

        try {
            int rowsAffected = statement.executeUpdate(deleteQuery);
            if(rowsAffected > 0) {
                System.out.println(rowsAffected + " rows with ID " + id + " deleted");
            } else {
                System.out.println("No row with ID " + id + " found");
            }

            return true;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean createProject(ProjectModel project) {
        String insertProjectQuery = "INSERT INTO Project (ID, ProjectName, TotalCharge, ProjectLeaderID) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertProjectQuery);

            preparedStatement.setInt(1, project.id);
            preparedStatement.setString(2, project.projectName);
            preparedStatement.setDouble(3, project.totalCharge);
            preparedStatement.setInt(4, project.projectLeaderID);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Project inserted successfully");
            }

            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public ProjectModel readProject(int id) {
        ProjectModel p = new ProjectModel();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Project WHERE ID=" + id);
            while(resultSet.next()) {
                p.id = resultSet.getInt("ID");
                p.projectName = resultSet.getString("ProjectName");
                p.totalCharge = resultSet.getDouble("TotalCharge");
                p.projectLeaderID = resultSet.getInt("ProjectLeaderID");
            }

            return p;
        } catch(Exception e) {
            e.printStackTrace();
        }

        return p;
    }

    public List<ProjectModel> readProjects() {
        List<ProjectModel> projects = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Project");
            while(resultSet.next()) {
                ProjectModel p = new ProjectModel();
                p.id = resultSet.getInt("ID");
                p.projectName = resultSet.getString("ProjectName");
                p.totalCharge = resultSet.getDouble("TotalCharge");
                p.projectLeaderID = resultSet.getInt("ProjectLeaderID");
                projects.add(p);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return projects;
    }

    public void updateAssignment(int projectID, int employeeID, double hours) {
        // Check if the assignment already exists
        String selectQuery = "SELECT * FROM Assignment WHERE ProjectID = ? AND EmployeeID = ?";
        try {

            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setInt(1, projectID);
            selectStatement.setInt(2, employeeID);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                // Assignment exists, update hours
                String updateQuery = "UPDATE Assignment SET HoursBilled = ? WHERE ProjectID = ? AND EmployeeID = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setDouble(1, hours);
                updateStatement.setInt(2, projectID);
                updateStatement.setInt(3, employeeID);
                updateStatement.executeUpdate();
            } else {
                // Assignment doesn't exist, insert a new row
                String insertQuery = "INSERT INTO Assignment (EmployeeID, ProjectID, HoursBilled, TotalCharge) VALUES (?, ?, ?, 0)";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                insertStatement.setInt(1, employeeID);
                insertStatement.setInt(2, projectID);
                insertStatement.setDouble(3, hours);
                insertStatement.executeUpdate();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void loadProjectsWithAssignment(List<ProjectModel> projectList)  {
        try {

            for(ProjectModel p : projectList) {


                // get sql assignment for p.id
                String query = "SELECT e.ID AS EmployeeID, e.EmployeeName, e.JobClassID AS JobID, jc.Wage, a.HoursBilled " +
                        "FROM Employee e " +
                        "INNER JOIN JobClass jc ON e.JobClassID = jc.ID " +
                        "INNER JOIN Assignment a ON e.ID = a.EmployeeID " +
                        "WHERE a.ProjectID = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, p.id);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int employeeId = resultSet.getInt("EmployeeID");
                    String employeeName = resultSet.getString("EmployeeName");
                    int jobId = resultSet.getInt("JobID");
                    double wage = resultSet.getDouble("Wage");
                    double hoursBilled = resultSet.getDouble("HoursBilled");


                    // create employee model
                    EmployeeModel employee = new EmployeeModel(employeeId, employeeName, jobId);
                    p.addAssignment(employee, wage, hoursBilled);
                }


            }


        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public boolean updateProject(ProjectModel project) {
        String query = "UPDATE Project SET ProjectName = ?, TotalCharge = ?, ProjectLeaderID = ? WHERE ID = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, project.projectName);
            preparedStatement.setDouble(2, project.totalCharge);
            preparedStatement.setInt(3, project.projectLeaderID);
            preparedStatement.setInt(4, project.id);

            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0) {
                System.out.println(rowsAffected + " row updated with ID " + project.id);
                return true;
            } else {
                System.out.println("No rows with ID " + project.id + " found");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteProject(int id) {
        String deleteQuery = "DELETE FROM Project WHERE ID=" + id;

        try {
            int rowsAffected = statement.executeUpdate(deleteQuery);
            if(rowsAffected > 0) {
                System.out.println(rowsAffected + " rows with ID " + id + " deleted");
            } else {
                System.out.println("No row with ID " + id + " found");
            }

            return true;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}

