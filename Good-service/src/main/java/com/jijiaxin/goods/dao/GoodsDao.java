package com.jijiaxin.goods.dao;

import com.jijiaxin.goods.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GoodsDao extends JpaRepository<Goods,Integer>,JpaSpecificationExecutor {
}
