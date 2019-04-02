package com.pinyougou.sellergoods.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.mapper.BrandMapper;
import com.pinyougou.pojo.Brand;
import com.pinyougou.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.*;

@Service(interfaceName = "com.pinyougou.service.BrandService")
/** 上面指定接口名，产生服务名，不然会用代理类的名称 */
@Transactional
public class BrandServiceImpl implements BrandService {

    /**
     * 注入数据访问接口代理对象
     * @return
     */
    @Autowired
    private BrandMapper brandMapper;
    /*
    添加品牌
     */
    @Override
    public void save(Brand brand) {
        brandMapper.insertSelective(brand);
    }
    /*
   修改品牌
     */
    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKey(brand);

    }
    //删除品牌
    @Override
    public void delete(Serializable id) {
        brandMapper.deleteByPrimaryKey(id);
    }
    //批量删除
    @Override
    public void deleteAll(Serializable[] ids) {
        try {
            brandMapper.deleteAll(ids);
        }catch (Exception e){
            throw new  RuntimeException(e);
        }
    }

    @Override
    public Brand findOne(Serializable id) {
        return null;
    }

    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    @Override
    public PageResult findByPage(Brand brand, int page, int rows) {
        /*
        分页查询品牌
         */
        PageInfo<Brand> pageInfo=PageHelper.startPage(page,rows).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                brandMapper.findAll(brand);
            }
        });
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }
    /*查询品牌列表（id与name）*/
    @Override
    public List<Map<String, Object>> findByIdAndName() {
        try{
            /*List<Brand> brandList = findAll();
            List<Map<String,Object>> data=new ArrayList<>();
            for (Brand brand : brandList) {
                Map<String,Object> map=new HashMap<>();
                map.put("id",brand.getId());
                map.put("name",brand.getName());
                data.add(map);
            }
            return data;*/
            return brandMapper.findByIdAndName();
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
}
