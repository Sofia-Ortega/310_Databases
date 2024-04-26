import java.util.Map;
import java.util.HashMap;
public class Main {
    public static void main(String[] args) {

        System.out.println("hello");
        Map<String, Integer> m = new HashMap<>();

        m.put("sofia", 10);
        m.put("dave", 2);

        System.out.println(m.get("sofia"));

        m.put("sofia", 23);
        System.out.println(m.get("sofia"));






        /*
        DataAccess dataAccess = new DataAccess();

        dataAccess.connect();

        ProjectModel tamu = new ProjectModel();
        tamu.id = 123;
        tamu.projectName = "tamu";
        tamu.projectLeaderID = 1001;
        tamu.totalCharge = 0.0;

        dataAccess.createProject(tamu);
        ProjectModel ans = dataAccess.readProject(tamu.id);
        System.out.println(ans.id + ", " + ans.projectName + ", " + ans.projectLeaderID + ", " + ans.totalCharge);

        tamu.projectName = "the best school";
        dataAccess.updateProject(tamu);
        ans = dataAccess.readProject(tamu.id);
        System.out.println(ans.id + ", " + ans.projectName + ", " + ans.projectLeaderID + ", " + ans.totalCharge);

        dataAccess.deleteProject(tamu.id);
        ans = dataAccess.readProject(tamu.id);
        System.out.println(ans.id + ", " + ans.projectName + ", " + ans.projectLeaderID + ", " + ans.totalCharge);


         */
    }
}