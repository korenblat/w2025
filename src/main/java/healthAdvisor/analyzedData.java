package healthAdvisor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class analyzedData {
   List<Double> parameterX= new ArrayList<>();
   String meaningOfX;
   String analysisResult;

    public analyzedData() {}
        
    public void analyzeDependency(String str) {
    	List<Double> levelOfSugar= new ArrayList<>();
        
    	levelOfSugar=extractData(str);
        double correlation = spearmanCorrelation(parameterX, levelOfSugar);
        if (parameterX.size()!=levelOfSugar.size()) {analysisResult = "Data is not consistent";}
        else
	        if (correlation > 0.5) {
	        	analysisResult = "There seems to be a strong tendency that when " + meaningOfX + " goes up, the level of sugar also tends to go up.";
	        } else if (correlation < -0.5) {
	        	analysisResult = "There seems to be a strong tendency that when " + meaningOfX + " goes up, the level of sugar tends to go down.";
	        } else {
	        	analysisResult = "There doesn't appear to be a clear relationship between " + meaningOfX + " and the level of sugar.";
	        }
    }

    double spearmanCorrelation(List<Double> x, List<Double> y) {
        // Calculation of Spearman's correlation coefficient is not implemented yet
    return -1;
    }
    
    public List<Double> extractData(String str) {  
    	List<Double> data= new ArrayList<>();;
    	
        // Establish database connection
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hw2?serverTimezone=IST", "root", "Aa12345")) {
            // Prepare SQL statement to fetch data
        	System.out.println(str);
            String sql = "SELECT level_of_sugar FROM hw2.data WHERE data.ID=?;";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, str);
            	// Execute query
                ResultSet resultSet = statement.executeQuery();
                
                // Retrieve data from result set
                while (resultSet.next()) {
                    data.add(resultSet.getDouble("level_of_sugar"));
                }
            }
        } catch (SQLException e) {e.printStackTrace(); }
     return data;
}

	public String getAnalysysResult() {
	return analysisResult;
	}
}
