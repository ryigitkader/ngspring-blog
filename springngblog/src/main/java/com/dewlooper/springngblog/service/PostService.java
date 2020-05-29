package com.dewlooper.springngblog.service;

import com.dewlooper.springngblog.dto.PostDto;
import com.dewlooper.springngblog.exception.PostNotFoundException;
import com.dewlooper.springngblog.model.Post;
import com.dewlooper.springngblog.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {

    @Autowired
    private AuthService authService;

    @Autowired
    private IPostRepository postRepository;

    public void createPost(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        User userName =  authService.getCurrentUser().orElseThrow(
                ()->new IllegalArgumentException("No user logged in"));
        post.setUserName(userName.getUsername());
        post.setCreatedOn(Instant.now());

        postRepository.save(post);

    }

    public List<PostDto> showAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapFromPostToDto).collect(toList());

    }

    private PostDto mapFromPostToDto(Post post) {

        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setUserName(post.getUserName());

        return postDto;
    }


    private Post mapFromPostDtoToPost(PostDto postDto){

        Post post =  new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        User loggedInUser = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User not found"));
        post.setCreatedOn(Instant.now());
        post.setUserName(loggedInUser.getUsername());
        post.setUpdatedOn(Instant.now());

        return post;


    }

    public PostDto readSinglePost(Long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id" + id));


        return mapFromPostToDto(post);
    }
}
