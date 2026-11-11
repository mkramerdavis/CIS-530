package edu.bellevue.cis530.studentai.dto; 
 
import com.fasterxml.jackson.annotation.JsonPropertyOrder; 
import java.util.List; 
import lombok.AllArgsConstructor; 
import lombok.Data; 
import lombok.NoArgsConstructor; 
 
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@JsonPropertyOrder({"summary", "actionItems", "importantTerms", "encouragement"}) 
public class StudyPlanResponse { 
 
    private String summary; 
    private List<String> actionItems; 
    private List<String> importantTerms; 
    private String encouragement; 
} 
