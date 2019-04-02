package com.pinyougou.mapper;

import tk.mybatis.mapper.common.Mapper;

import com.pinyougou.pojo.TypeTemplate;

import java.io.Serializable;
import java.util.List;

/**
 * TypeTemplateMapper 数据访问接口
 * @date 2019-03-28 20:00:33
 * @version 1.0
 */
public interface TypeTemplateMapper extends Mapper<TypeTemplate>{

    /** 多条件分页查询 */
    List<TypeTemplate> findAll(TypeTemplate typeTemplate);
    /*自定义批量删除*/
    void deleteAll(Serializable[] ids);
}