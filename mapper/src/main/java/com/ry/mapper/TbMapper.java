package com.ry.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author renyang
 */
@Mapper
public interface TbMapper {

    int updateById(@Param("id") int id);
}