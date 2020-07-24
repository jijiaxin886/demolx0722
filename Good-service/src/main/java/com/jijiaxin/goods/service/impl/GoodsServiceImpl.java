package com.jijiaxin.goods.service.impl;

import com.jijiaxin.goods.dao.GoodsDao;
import com.jijiaxin.goods.entity.Goods;
import com.jijiaxin.goods.service.GoodsService;
import org.mockito.internal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public Page<Goods> getGoodsList(Integer current, Integer size, Goods goods) {
       Specification<Goods> specification= new Specification<Goods>() {
            @Override
            public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (StringUtils.isEmpty(goods.getGname())){
                    Predicate gname = criteriaBuilder.like(root.get("gname"), "%".concat(goods.getGname()).concat("%"));
                    list.add(gname);
                }
                if (!StringUtils.isEmpty(goods.getStartPrice())&&!StringUtils.isEmpty(goods.getEndPrice())){
                    Predicate price = criteriaBuilder.between(root.get("price"), goods.getStartPrice(), goods.getEndPrice());
                    list.add(price);
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        PageRequest pageable = PageRequest.of(current - 1, size, Sort.Direction.ASC,"gid");
        return goodsDao.findAll(specification,pageable);
    }
}
