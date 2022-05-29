package com.example.adddata.Service.impl;

import com.example.adddata.Bean.Data;
import com.example.adddata.Mapper.DataMapper;
import com.example.adddata.Service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataServiceImpl implements DataService {
    @Autowired
    DataMapper dataMapper;
    @Override
    public String getData() {
        Data data = dataMapper.getData();
        String data1 = data.getData();
        return data1;
    }

    @Override
    public void addData(String data) {
        Integer A = Integer.parseInt(data);
        Data data1 = dataMapper.getData();
        String data2 = data1.getData();
        Integer B = Integer.parseInt(data2);
        dataMapper.addData(String.valueOf((B+A)));
    }

    @Override
    public void reduceData(String data) {
        Integer A = Integer.parseInt(data);
        Data data1 = dataMapper.getData();
        String data2 = data1.getData();
        Integer B = Integer.parseInt(data2);
        dataMapper.reduceData(String.valueOf((B-A)));
    }
}
