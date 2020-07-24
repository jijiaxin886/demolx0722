package com.jijiaxin.goods.dao;

import com.jijiaxin.goods.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressDao extends JpaRepository<Address,Integer>{
    List<Address> findByPid(Integer i);
}
