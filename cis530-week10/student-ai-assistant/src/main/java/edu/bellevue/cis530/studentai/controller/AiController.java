package edu.bellevue.cis530.studentai.controller; 
 
import edu.bellevue.cis530.studentai.dto.AiQuestionRequest; 
import edu.bellevue.cis530.studentai.dto.AiTextResponse; 
import edu.bellevue.cis530.studentai.dto.ContextHelpRequest; 
import edu.bellevue.cis530.studentai.dto.RagHelpRequest; 
import edu.bellevue.cis530.studentai.dto.StudyPlanRequest; 
import edu.bellevue.cis530.studentai.dto.StudyPlanResponse; 
import edu.bellevue.cis530.studentai.service.AiService; 
import jakarta.validation.Valid; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*; 
 
/** 
 * This controller exposes the REST endpoints required by the assignment. 
 * Each method matches one endpoint from the Week 10 instructions. 
 */ 
@RestController 
@RequestMapping("/api/ai") 
public class AiController { 
 
    private final AiService aiAssistantService; 
 
    /** 
     * Constructor injection gives this controller access to the service layer. 
     */ 
    public AiController(AiService aiAssistantService) { 
        this.aiAssistantService = aiAssistantService; 
    } 
 
    /** 
     * Endpoint 1: plain prompt-based question answering. 
     */ 
    @PostMapping("/ask") 
    public ResponseEntity<AiTextResponse> ask(@Valid @RequestBody AiQuestionRequest request) { 
        return ResponseEntity.ok(aiAssistantService.askQuestion(request));
    } 
 
    /** 
     * Endpoint 2: structured output mapped into a Java DTO. 
     */ 
    @PostMapping("/study-plan") 
    public ResponseEntity<StudyPlanResponse> studyPlan(@Valid @RequestBody StudyPlanRequest request) { 
        return ResponseEntity.ok(aiAssistantService.createStudyPlan(request)); 
    } 
 
    /** 
     * Endpoint 3: bring-your-own-data by manually inserting notes into the prompt. 
     */ 
    @PostMapping("/context-help") 
    public ResponseEntity<AiTextResponse> contextHelp(@Valid @RequestBody ContextHelpRequest request) { 
        return ResponseEntity.ok(aiAssistantService.contextHelp(request));
    } 
 
    /** 
     * Endpoint 4: RAG using a vector store and QuestionAnswerAdvisor. 
     */ 
    @PostMapping("/rag-help") 
    public ResponseEntity<AiTextResponse> ragHelp(@Valid @RequestBody RagHelpRequest request) { 
        return ResponseEntity.ok(aiAssistantService.ragHelp(request));
    }
}