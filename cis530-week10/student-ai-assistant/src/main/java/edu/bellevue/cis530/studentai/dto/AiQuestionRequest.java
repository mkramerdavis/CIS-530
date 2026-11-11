package edu.bellevue.cis530.studentai.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor; 
import lombok.Data;
import lombok.NoArgsConstructor; 
 
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class AiQuestionRequest { 
 
    @NotBlank(message = "question is required") 
    private String question; 
} 
