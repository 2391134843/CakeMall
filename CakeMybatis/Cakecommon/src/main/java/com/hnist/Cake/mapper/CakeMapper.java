package com.hnist.Cake.mapper;

import com.hnist.Cake.entity.Cake;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 蛋糕
 */
public interface CakeMapper {
    /**
     * 分页查询蛋糕
     * @param skip 跳过的记录数，从那条开始查询
     * @param size 要查询的记录数
     * @return 蛋糕集合
     */
    @Select("select *from cake order by create_time desc limit #{skip},#{size}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "name",property = "name"),
            @Result(column = "level",property = "level"),
            @Result(column = "price",property = "price"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "create_time",property = "updateTime"),
    })
    List<Cake> getCakes(Integer skip, Integer size);

    /**
     * 根据分类分页查询蛋糕
     *
     * @param categoryId 蛋糕分类id
     * @param skip       跳过的记录数
     * @param size       要查询的记录数
     * @return
     */
    @Select("select id ,category_id categoryId,name,level ,price,create_time createTime," +
            "update_time updateTime" +
            "from cake order by create_time desc limit #{skip},#{size}")
    List<Cake> getCakeByCategoryId(@Param("categoryId") Long categoryId, Integer skip, Integer size);

    @Select("select count(*) from cake where category_id = #{categoryId}")
    int countCakesByCategoryId(@Param("categoryId") Long categoryId);

    @Select("insert into cake(category_id,name,level,price,small_img,create_time" +
            "update_time value(#{cake.categoryId)},#{cake.level},#{cake.price},#{cake.smallImg}" +
            "#{cake.createTime},#{cake.updateTime})")
    void addCake(@Param("cake") Cake cake);

    @Select("select small_img smallImg from cake where id =#{id} for update")
    Cake getImg(@Param("id")Long id);
}
