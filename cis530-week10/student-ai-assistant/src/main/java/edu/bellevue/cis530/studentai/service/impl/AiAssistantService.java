package edu.bellevue.cis530.studentai.service.impl;

 
import edu.bellevue.cis530.studentai.dto.AiQuestionRequest; 
import edu.bellevue.cis530.studentai.dto.AiTextResponse; 
import edu.bellevue.cis530.studentai.dto.ContextHelpRequest; 
import edu.bellevue.cis530.studentai.dto.RagHelpRequest; 
import edu.bellevue.cis530.studentai.dto.StudyPlanRequest; 
import edu.bellevue.cis530.studentai.dto.StudyPlanResponse; 
import edu.bellevue.cis530.studentai.service.AiService; 
import edu.bellevue.cis530.studentai.service.DocumentLoaderService; 
import org.springframework.ai.chat.client.ChatClient; 
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor; 
import org.springframework.ai.vectorstore.VectorStore; 
import org.springframework.stereotype.Service; 
 
/** 
 * This is the main business-logic class. 
 * The controller receives HTTP requests, but this service does the actual AI work. 
 */ 
@Service 
public class AiAssistantService implements AiService { 
 
    private final ChatClient chatClient; 
    private final VectorStore vectorStore; 
    private final DocumentLoaderService documentLoaderService; 
 
    /** 
     * Constructor injection gives this service access to Spring AI and local documents. 
     */ 
    public AiAssistantService(ChatClient chatClient, 
                              VectorStore vectorStore, 
                              DocumentLoaderService documentLoaderService) { 
        this.chatClient = chatClient; 
        this.vectorStore = vectorStore; 
        this.documentLoaderService = documentLoaderService; 
    } 
 
    /** 
     * Endpoint logic for /api/ai/ask. 
     * This method shows the simplest use of Spring AI: send a prompt and return text. 
     */ 
    public AiTextResponse askQuestion(AiQuestionRequest request) { 
        // Sends the student's question to the AI model and retrieves the response.
        String answer = chatClient.prompt()
            .user(request.getQuestion())
            .call()
            .content();
        return new AiTextResponse(answer, "openai"); 
    } 
 
 
    /** 
     * Endpoint logic for /api/ai/study-plan. 
     * This method asks the model for structured data and maps the result into a DTO. 
     */ 
    public StudyPlanResponse createStudyPlan(StudyPlanRequest request) { 
        return chatClient.prompt() 
                .system(""" 
                        Return a study plan as a Java-friendly structured response. 
                        Include these fields only: 
                        summary, 
                        actionItems, 
                        importantTerms, 
                        encouragement. 
                        actionItems should contain exactly 3 items. 
                        importantTerms should contain 4 or 5 short terms. 
                        """) 
                .user(user -> user.text(""" 
                        Student name: {studentName} 
                        Topic: {topic} 
                        Problem: {problem} 
                        Goal: {goal} 
 
                        Create a short study plan for this student. 
                        """) 
                        .param("studentName", request.getStudentName()) 
                        .param("topic", request.getTopic()) 
                        .param("problem", request.getProblem()) 
                        .param("goal", request.getGoal())) 
                .call() 
                .entity(StudyPlanResponse.class); 
    } 
 
 
 
    /** 
     * Endpoint logic for /api/ai/context-help. 
     * This method demonstrates "bring your own data" by stuffing local notes into 
     * the prompt. 
     */ 
    public AiTextResponse contextHelp(ContextHelpRequest request) { 
        String notes = documentLoaderService.loadWeek10Notes(); 
 
        String answer = chatClient.prompt() 
                .system("Answer using the provided course notes first. If the notes are not enough, say that clearly.") 
                .user(user -> user.text(""" 
                        Course notes: 
                        {notes} 
 
                        Student question: 
                        {question} 
                        """) 
                        .param("notes", notes) 
                        .param("question", request.getQuestion())) 
                .call() 
                .content(); 
 
        return new AiTextResponse(answer, "openai"); 
    } 
 
    /** 
     * Endpoint logic for /api/ai/rag-help. 
     * This method demonstrates introductory RAG with a vector store and advisor. 
     */ 
    public AiTextResponse ragHelp(RagHelpRequest request) { 
        // Uses the vector store to retrieve relevant documents before generating an answer.
        String answer = chatClient.prompt()
            .advisors(QuestionAnswerAdvisor.builder(vectorStore).build())
            .user(request.getQuestion())
            .call()
            .content(); 
        return new AiTextResponse(answer, "openai"); 
    } 
}
