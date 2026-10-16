/*
 * Bellevue University. (n.d.). Assignment 6.2 – Consuming RESTful Web Services with WebClient. Course Handout.
 */

package edu.bellevue.cis530.week6.client;


import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import edu.bellevue.cis530.week6.dto.PostDto;
import edu.bellevue.cis530.week6.exception.ApiClientException;
import edu.bellevue.cis530.week6.exception.ResourceNotFoundException;


@Component 
public class JsonPlaceholderClient {


    private final WebClient webClient;


    public JsonPlaceholderClient(WebClient webClient) {
        this.webClient = webClient;
    }

    /*
     * This method retrieves all posts from the JSONPlaceholder API using WebClient.
     * It sends a GET request to the /posts endpoint, retrieves the response, and collects the results into a list of PostDto objects.
     * If no posts are found, it returns an empty list.
     */
    public List<PostDto> getAllPosts() {
        List<PostDto> posts = webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToFlux(PostDto.class)
                .collectList()
                .block();

        return posts != null ? posts : Collections.emptyList();
    }

    /*
     * This method retrieves a post by its ID from the JSONPlaceholder API using WebClient.
     * It constructs the URI with the provided ID, retrieves the response, and returns a PostDto object.
     * If the post is not found, it throws a ResourceNotFoundException.
     */
    public PostDto getPostById(Long id) {
        try {
            PostDto post = webClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .bodyToMono(PostDto.class)
                .block();

            if (post == null) {
                throw new ResourceNotFoundException(
                    "Post with ID " + id + " was not found");
            }

            return post;
        }
        catch (WebClientResponseException.NotFound ex) {
            throw new ResourceNotFoundException(
                "Post with ID " + id + " was not found");
        }

        catch (ResourceNotFoundException ex) {
            throw ex;
        }

        catch (Exception ex) {
            throw new ApiClientException(
                "Failed to retrieve post with ID " + id, ex);
        }
    }

    /*
     * This method retrieves posts by userId from the JSONPlaceholder API using WebClient.
     * It constructs the URI with a query parameter for userId, retrieves the response, and collects the results into a list of PostDto objects.
     * If no posts are found, it returns an empty list.
     */
    public List<PostDto> getPostsByUserId(Long userId) {
        List<PostDto> posts = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/posts")
                        .queryParam("userId", userId)
                        .build())
                .retrieve()
                .bodyToFlux(PostDto.class)
                .collectList()
                .block();

        return posts != null ? posts : Collections.emptyList();
    }
    
}
