package org.weicong.uas.service.impl;

import org.springframework.stereotype.Service;
import org.weicong.uas.entity.SysUser;
import org.weicong.uas.mapper.SysUserMapper;
import org.weicong.uas.service.ISysUserService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.AllArgsConstructor;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author weicong
 * @since 2019-06-28
 */
@AllArgsConstructor
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

	private final SysUserMapper sysUserMapper;
	
	@Override
	public SysUser getByUsername(String username) {
		return sysUserMapper.selectByUsername(username);
	}

}
