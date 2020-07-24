package com.jijiaxin.goods.service.impl;

import com.jijiaxin.goods.dao.AddressDao;
import com.jijiaxin.goods.entity.Address;
import com.jijiaxin.goods.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressDao addressDao;

    @Override
    public Object getAddressList() {
        List<Address> list = addressDao.findByPid(0);
        list.forEach(address -> {
            List<Address> two = addressDao.findByPid(address.getAid());
            address.setAddressList(two);
        });
        return list;
    }
}
