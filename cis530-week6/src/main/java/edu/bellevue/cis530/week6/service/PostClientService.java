package edu.bellevue.cis530.week6.service;

import java.util.List;
import edu.bellevue.cis530.week6.dto.PostDto;

public interface PostClientService {

    List<PostDto> getAllPosts();

    PostDto getPostById(Long id);

    List<PostDto> getPostsByUserId(Long userId);
}
