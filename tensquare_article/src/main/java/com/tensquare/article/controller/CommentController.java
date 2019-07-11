package com.tensquare.article.controller;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 新增评论
     *
     * @param comment
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Comment comment) {
        commentService.add(comment);
        return new Result(true, StatusCode.OK, "提交成功 ");
    }

    /**
     * 查询评论
     *
     * @param articleid
     * @return
     */
    @RequestMapping(value = "/article/{articleid}", method = RequestMethod.GET)
    public Result findByArticleid(@PathVariable String articleid) {
        return new Result(true, StatusCode.OK, "查询成功", commentService.findByArticleid(articleid));
    }
}
