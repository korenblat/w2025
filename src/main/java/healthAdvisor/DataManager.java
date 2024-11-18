package healthAdvisor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
	List<Double> parameterX = new ArrayList<>();
    List<Double> levelOfSugar = new ArrayList<>();

    public static void main(String[] args) {
        analyzedData data = new analyzedData();
        data.meaningOfX="physical activity"; 
    	for (int i=0;i<6;i++) {
            data.parameterX.add(1.0);
    	};
        
        data.analyzeDependency("experiment 1");
        System.out.println(data.getAnalysysResult());
    }
}
