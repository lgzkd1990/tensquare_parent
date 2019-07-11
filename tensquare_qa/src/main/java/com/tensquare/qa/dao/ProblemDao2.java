package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao2 extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
    /**
     * 根据标签ID查询最新问题列表
     * @param labelId
     * @param pageable
     * @return
     */
    @Query(value = "select * from tb_problem,tb_pl where  id=problemid and labelid=? order by repylytime desc",nativeQuery = true)
    public Page<Problem> findNewListByLabelId(String labelId, Pageable pageable);
    /**
     * 根据标签ID查询热门问题列表
     * @param labelId
     * @param pageable
     * @return
     */
    @Query(value = "select * from tb_problem,tb_pl where  id=problemid and labelid=? order by reply desc",nativeQuery = true)
    public Page<Problem> findHotListByLabelId(String labelId, Pageable
            pageable);
    /**
     * 根据标签ID查询等待回答列表
     * @param labelId
     * @param pageable
     * @return
     */
    @Query(value = "select * from tb_problem,tb_pl where  id=problemid and labelid=? and reply=0 order by createtime desc",nativeQuery = true)
    public Page<Problem> findWaitListByLabelId(String labelId, Pageable
            pageable);
}
