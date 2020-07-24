package com.jijiaxin.goods.service;

import com.jijiaxin.goods.entity.Goods;
import org.springframework.data.domain.Page;

public interface GoodsService {
    Page<Goods> getGoodsList(Integer current,Integer size,Goods goods);
}
