package org.weicong.uas.mapper;

import org.apache.ibatis.annotations.Param;
import org.weicong.uas.entity.SysUser;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author weicong
 * @since 2019-06-28
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

	SysUser selectByUsername(@Param("username") String username);
	
	SysUser selectByPhone(@Param("phone") String phone);
	
	SysUser selectByQQOpenid(@Param("qqOpenid") String qqOpenid);
	
	SysUser selectByWXOpenid(@Param("wxOpenid") String wxOpenid);
}
