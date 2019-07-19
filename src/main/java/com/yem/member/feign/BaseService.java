package com.yem.member.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yem.utils.ServerUtils;

@FeignClient(value=ServerUtils.YEM_BASE)
public interface BaseService {

	@RequestMapping(value = ServerUtils.BaseServer.MCCODE_PATH, method = RequestMethod.POST)
	public Long getMcCode(String mcCodeType);

}
