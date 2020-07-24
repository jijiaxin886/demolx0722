package com.jijiaxin.goods.controller;

import com.jijiaxin.goods.dao.GoodsDao;
import com.jijiaxin.goods.entity.Goods;
import com.jijiaxin.goods.service.GoodsService;
import com.jijiaxin.goods.vo.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    GoodsDao goodsDao;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("list")
    public ResultEntity getGoodsList(@RequestParam(defaultValue = "1")Integer current, @RequestParam(defaultValue = "1")Integer size, Goods goods){
        return ResultEntity.ok(goodsService.getGoodsList(current,size,goods));
    }

    @RequestMapping("doDeletes")
    public ResultEntity deDeletes(Integer[] gids){
        try {
            for (int gid:gids){
                goodsDao.deleteById(gid);
            }
            return ResultEntity.ok("删除成功啦！");
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultEntity.error("删除失败，请重试！");
    }

    @RequestMapping("/upload")
    public ResultEntity upload(MultipartFile file){
        try {
            if (file!=null&&!file.isEmpty()){
                String path = "J:\\pic\\";
                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                File destFile = new File(path,fileName);
                file.transferTo(destFile);
                return ResultEntity.ok("http://localhost:8186/img/"+fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultEntity.error("upload");
    }

    @RequestMapping("doAddOrUpdate")
    public ResultEntity doAddOrUpdate(@RequestBody Goods goods){
        try {
            goods.setCreateDate(new Date());
            goodsDao.save(goods);
            return ResultEntity.ok("成功了");
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultEntity.error("失败了");
    }
}
