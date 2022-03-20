package com.ry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ry.entity.ProductTest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author renyang
 */
@Mapper
public interface ProductTestMapper extends BaseMapper<ProductTest> {

    /**
     * 更新库存
     *
     * @param id
     * @param count
     * @return
     */
    int updateCount(@Param("id") int id , @Param("count") int count);


}