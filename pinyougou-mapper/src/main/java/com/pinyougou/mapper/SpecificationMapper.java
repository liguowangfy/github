package com.pinyougou.mapper;

import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import com.pinyougou.pojo.Specification;

import java.util.List;
import java.util.Map;

/**
 * SpecificationMapper 数据访问接口
 * @date 2019-03-28 20:00:33
 * @version 1.0
 */
public interface SpecificationMapper extends Mapper<Specification>{

    /*查询品牌列表（id与name）*/
   List<Specification> findAll(Specification specification);
    @Select("SELECT id,spec_name as text from tb_specification order by id asc")
    List<Map<String,Object>> findByIdAndSpecName();
}