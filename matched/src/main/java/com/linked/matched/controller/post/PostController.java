package com.linked.matched.controller.post;

import com.linked.matched.request.post.PostCreate;
import com.linked.matched.request.post.PostEdit;
import com.linked.matched.request.post.PostSearch;
import com.linked.matched.response.ResponseDto;
import com.linked.matched.response.post.PostOneResponse;
import com.linked.matched.response.post.PostResponse;
import com.linked.matched.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public ResponseEntity<HttpStatus> healthCheck(){
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/home")
    public List<PostResponse> webHome(){
        return postService.homeList();
    }

    @GetMapping("/board/{boardName}")//RequestBody이 아니라 ModelAttribute로 넣어야한다.
    public List<PostResponse> viewList(@PathVariable String boardName,@PathParam(value = "page") Long page){
        return postService.getList(boardName, Math.toIntExact(page));
    }

    @PostMapping("/board/{boardName}") //게시글 작성이 되었습니다.
    public ResponseEntity<Object> createPost(@PathVariable String boardName, @RequestBody PostCreate postCreate,Principal principal){
        postService.write(postCreate,principal);//dto로 만들어서 만들때 필요한것을 넣어준다.
        return new ResponseEntity<>(new ResponseDto("게시글 작성이 되었습니다."), HttpStatus.OK);

    }

    @GetMapping("/board/{boardName}/{postId}")
    public PostOneResponse viewPost(@PathVariable String boardName, @PathVariable Long postId){
        return postService.findPost(postId);
    }

    @PatchMapping("/board/{boardName}/{postId}")// 게시글이 수정 되었습니다.
    public ResponseEntity<Object> editPost(@PathVariable String boardName, @PathVariable Long postId,@RequestBody PostEdit postEdit, Principal principal){
        //request body
        if(postService.edit(postId, postEdit,principal)) {
            return new ResponseEntity<>(new ResponseDto("게시글이 수정 되었습니다."), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDto("수정할 권한이 없습니다."), HttpStatus.OK);
    }

    @DeleteMapping("/board/{boardName}/{postId}") // 게시글이 삭제 되었습니다.
    public ResponseEntity<Object> deletePost(@PathVariable String boardName, @PathVariable Long postId, Principal principal)  {
        if(postService.delete(postId,principal)) {
            return new ResponseEntity<>(new ResponseDto("게시글이 삭제 되었습니다."), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDto("삭제할 권한이 없습니다."), HttpStatus.OK);
    }
    
    //카테고리 넣는 검색
    @GetMapping("/board/{boardName}/search")
    public List<PostResponse> searchPost(@PathVariable String boardName,@RequestParam String keyword,@ModelAttribute PostSearch postSearch){
        return postService.searchPost(boardName,keyword,postSearch);
    }


}
