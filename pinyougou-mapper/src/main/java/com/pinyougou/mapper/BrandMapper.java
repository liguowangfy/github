package com.pinyougou.mapper;

import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import com.pinyougou.pojo.Brand;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * BrandMapper 数据访问接口
 * @date 2019-03-28 20:00:33
 * @version 1.0
 */
public interface BrandMapper extends Mapper<Brand>{


    List<Brand> findAll(Brand brand);
    /*批量删除  自定义方法*/
    void deleteAll(Serializable[] ids);
    /*查询品牌列表（id与name）*/
    @Select("SELECT id,name as text from tb_brand")
    List<Map<String,Object>> findByIdAndName();
}