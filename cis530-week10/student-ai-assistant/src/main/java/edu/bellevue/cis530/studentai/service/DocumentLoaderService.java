package edu.bellevue.cis530.studentai.service;

import org.springframework.ai.document.Document; 
import org.springframework.core.io.ClassPathResource; 
import org.springframework.stereotype.Service; 
 
import java.io.IOException; 
import java.nio.charset.StandardCharsets; 
import java.util.List; 
import java.util.Map; 
 
/** 
 * This service reads the local text files used by the assignment. 
 * We separate file reading from AI prompting so the project is easier to 
understand. 
 */ 
@Service 
public class DocumentLoaderService { 
 
    /** 
     * We need this method for the context-help endpoint. 
     * It loads one file and returns plain text that can be stuffed into the 
prompt. 
     */ 
    public String loadWeek10Notes() { 
        return readFile("docs/week10_notes.txt"); 
    } 
 
    /** 
     * We need this method for the RAG endpoint. 
     * It turns local files into Document objects that can be added to the vector 
store. 
     */ 
    public List<Document> loadRagDocuments() { 
        return List.of( 
                new Document(readFile("docs/week10_notes.txt"), Map.of("source", 
"week10_notes.txt")), 
                new Document(readFile("docs/spring_ai_faq.txt"), Map.of("source", 
"spring_ai_faq.txt")), 
                new Document(readFile("docs/rag_basics.txt"), Map.of("source", 
"rag_basics.txt")) 
        ); 
    } 
 
    /** 
     * Helper method used by both public methods above. 
     * We keep file-reading logic in one place so the code is easier to maintain. 
     */ 
    private String readFile(String path) { 
        try { 
            ClassPathResource resource = new ClassPathResource(path); 
            try (var inputStream = resource.getInputStream()) { 
                return new String(inputStream.readAllBytes(), 
StandardCharsets.UTF_8); 
            } 
        } 
        catch (IOException exception) { 
            throw new IllegalStateException("Could not read resource file: " + 
path, exception); 
        } 
    } 
} 
