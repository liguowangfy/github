package com.pinyougou.manager.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.pojo.Brand;
import com.pinyougou.service.BrandService;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 品牌的控制器
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    /**
     * 引用服务接口代理对象
     * timeout: 调用服务接口方法超时时间毫秒数
     */
    @Reference(timeout = 10000)
    private BrandService brandService;

    /*查询全部品牌*/
    @GetMapping("/findAll")
    public List<Brand> findAll(){
        return brandService.findAll();
    }
    /*
    添加品牌
     */
    @PostMapping("/save")
    public boolean save(@RequestBody Brand brand){
        try {
            brandService.save(brand);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /*
    修改品牌
     */
    @PostMapping("/update")
    public boolean update(@RequestBody Brand brand){
        try {
            brandService.update(brand);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    /*
    分页查询品牌
    多条件分页查询
     */
    @GetMapping("/findByPage")
    public PageResult findByPage(Brand brand,Integer page, Integer rows){
        /*GET请求中文转码*/
        if(brand !=null && org.apache.commons.lang3.StringUtils.isNoneBlank(brand.getName())){
            try {
                brand.setName(new String(brand.getName().getBytes("ISO8859-1"),"UTF-8"));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return brandService.findByPage(brand,page,rows);
    }
    /*
    删除的方法
     */
    @GetMapping("/delete")
    public boolean delete(Long[] ids){
        try {
            brandService.deleteAll(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /*查询品牌列表（id与name）*/
    @GetMapping("/findBrandList")
    public List<Map<String,Object>> findBrandList(){
        return brandService.findByIdAndName();
    }
}
