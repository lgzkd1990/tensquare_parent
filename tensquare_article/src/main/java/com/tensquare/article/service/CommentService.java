package com.tensquare.article.service;

import com.tensquare.article.dao.CommentDao;
import com.tensquare.article.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 新增评论
     *
     * @param comment
     */
    public void add(Comment comment) {
        comment.set_id(idWorker.nextId() + "");
        commentDao.save(comment);
    }

    /**
     * 找寻评论
     *
     * @param articleid
     * @return
     */
    public List<Comment> findByArticleid(String articleid) {
        return commentDao.findByArticleid(articleid);
    }
}