package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.mapper.SpecificationMapper;
import com.pinyougou.mapper.SpecificationOptionMapper;
import com.pinyougou.pojo.Specification;
import com.pinyougou.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service(interfaceName = "com.pinyougou.service.SpecificationService")
@Transactional
public class SpecificationServiceImpl implements SpecificationService {
    //注入数据访问接口代理对象
    @Autowired
    private SpecificationMapper specificationMapper;
    //注入重表的接口代理对象
    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;
    /*
    添加规格
     */
    @Override
    public void save(Specification specification) {
        try {
            //1.往tb_specification表插入数据
            specificationMapper.insertSelective(specification);
            //2.往tb_specigication_option表插入数据（批量插入）
            specificationOptionMapper.save(specification);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Specification specification) {

    }

    @Override
    public void delete(Serializable id) {

    }

    @Override
    public void deleteAll(Serializable[] ids) {

    }

    @Override
    public Specification findOne(Serializable id) {
        return null;
    }

    @Override
    public List<Specification> findAll() {
        return null;
    }
    /*
    多条件分页查询规格
     */
    @Override
    public PageResult findByPage(Specification specification, int page, int rows) {

        try {
        /*
        开始分页
         */
            PageInfo<Specification> pageInfo = PageHelper.startPage(page, rows).doSelectPageInfo(new ISelect() {
                @Override
                public void doSelect() {
                    specificationMapper.findAll(specification);
                }
            });
            return new PageResult(pageInfo.getTotal(),pageInfo.getList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*查询品牌列表（id与name）*/
    @Override
    public List<Map<String, Object>> findByIdAndSpecName() {
        return  specificationMapper.findByIdAndSpecName();
    }


}
