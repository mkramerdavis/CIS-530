package edu.bellevue.cis530.studentai.service; 
 
import edu.bellevue.cis530.studentai.dto.AiQuestionRequest; 
import edu.bellevue.cis530.studentai.dto.AiTextResponse;
import edu.bellevue.cis530.studentai.dto.ContextHelpRequest;
import edu.bellevue.cis530.studentai.dto.RagHelpRequest; 
import edu.bellevue.cis530.studentai.dto.StudyPlanRequest; 
import edu.bellevue.cis530.studentai.dto.StudyPlanResponse; 
 
public interface AiService { 
 
    AiTextResponse askQuestion(AiQuestionRequest request); 
 
    StudyPlanResponse createStudyPlan(StudyPlanRequest request); 
 
    AiTextResponse contextHelp(ContextHelpRequest request); 
 
    AiTextResponse ragHelp(RagHelpRequest request); 
}
