package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.mapper.TypeTemplateMapper;
import com.pinyougou.pojo.TypeTemplate;
import com.pinyougou.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service(interfaceName ="com.pinyougou.service.TypeTemplateService" )
@Transactional
public class TypeTemplateServiceImpl implements TypeTemplateService {
    @Autowired
    private TypeTemplateMapper typeTemplateMapper;

    /*添加类型模板*/
    @Override
    public void save(TypeTemplate typeTemplate) {
            typeTemplateMapper.insertSelective(typeTemplate);
    }
    /*修改类型模板*/
    @Override
    public void update(TypeTemplate typeTemplate) {
        typeTemplateMapper.updateByPrimaryKeySelective(typeTemplate);
    }
    /*删除类型模板*/
    @Override
    public void delete(Serializable id) {
        typeTemplateMapper.deleteByPrimaryKey(id);
    }
    /*批量删除类型模板*/
    @Override
    public void deleteAll(Serializable[] ids) {
        try {
            typeTemplateMapper.deleteAll(ids);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TypeTemplate findOne(Serializable id) {
        return null;
    }

    @Override
    public List<TypeTemplate> findAll() {
        return null;
    }
    /** 分页查询类型模版 */
    @Override
    public PageResult findByPage(TypeTemplate typeTemplate, int page, int rows) {
        try{
            PageInfo<TypeTemplate> pageInfo = PageHelper.startPage(page, rows).doSelectPageInfo(new ISelect() {
                @Override
                public void doSelect() {
                    typeTemplateMapper.findAll(typeTemplate);
                }
            });
                return new PageResult(pageInfo.getTotal(),pageInfo.getList());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
