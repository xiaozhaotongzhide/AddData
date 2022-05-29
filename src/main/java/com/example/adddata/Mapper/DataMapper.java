package com.example.adddata.Mapper;

import com.example.adddata.Bean.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface DataMapper {

    @Select("select * from data where id = 1")
    Data getData();

    @Update("UPDATE data set data = #{data} where id = 1")
    void addData(String data);

    @Update("UPDATE data set data = #{data} where id = 1")
    void reduceData(String data);
}
