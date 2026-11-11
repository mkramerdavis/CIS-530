package edu.bellevue.cis530.studentai.dto; 
 
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor; 
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class StudyPlanRequest { 
 
    @NotBlank(message = "studentName is required") 
    private String studentName; 
 
    @NotBlank(message = "topic is required") 
    private String topic; 
 
    @NotBlank(message = "problem is required") 
    private String problem; 
 
    @NotBlank(message = "goal is required") 
    private String goal; 
} 
