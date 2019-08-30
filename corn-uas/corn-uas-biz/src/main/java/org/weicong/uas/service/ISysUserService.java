package org.weicong.uas.service;

import org.weicong.uas.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author weicong
 * @since 2019-06-28
 */
public interface ISysUserService extends IService<SysUser> {
	
	SysUser getByUsername(String username);

}
