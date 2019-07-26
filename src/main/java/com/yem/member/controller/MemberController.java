package com.yem.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.yem.common.BaseMsgResponse;
import com.yem.constant.ApiConstant;
import com.yem.constant.Constants;
import com.yem.dto.YemMemberDTO;
import com.yem.entity.YemMember;
import com.yem.enums.McCodeTypeEnum;
import com.yem.member.feign.BaseService;
import com.yem.member.service.MemberService;
import com.yem.request.AddYemMemberRequest;
import com.yem.request.ModifyYemMemberRequest;
import com.yem.request.QueryYemMemberListRequest;
import com.yem.request.QueryYemMemberRequest;
import com.yem.response.AddYemMemberResponse;
import com.yem.response.ModifyYemMemberResponse;
import com.yem.response.QueryYemMemberListResponse;
import com.yem.response.QueryYemMemberResponse;
import com.yem.utils.DateUtil;

import lombok.extern.slf4j.Slf4j;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

/**
 * 会员业务处理
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2019年7月10日 下午1:53:37 <br/>
 *
 * @author <a href="mailto:hbszguchuang@163.com">guchuang</a>
 * @version 
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/member")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BaseService baseService;

    @GetMapping("current")
    public Principal user(Principal principal) {
        return principal;
    }

    @PostMapping("/query")
    public BaseMsgResponse query(HttpServletRequest request) {
    	log.info("开始会员详情查询接口");
    	
    	QueryYemMemberResponse resp = new QueryYemMemberResponse();
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	
    	QueryYemMemberRequest reqModel = (QueryYemMemberRequest) JSONObject.parseObject(param, 
    			QueryYemMemberRequest.class);
    	
    	resp.setYemMember(memberService.getMember(reqModel.getMemberCode()));
    	
    	return resp;
    }

    @PostMapping("/list")
    public BaseMsgResponse queryList(HttpServletRequest request) {
    	log.info("开始会员列表查询接口");
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	QueryYemMemberListResponse resp = new QueryYemMemberListResponse();
    	
    	QueryYemMemberListRequest reqModel = (QueryYemMemberListRequest) JSONObject.parseObject(param, 
    			QueryYemMemberListRequest.class);

    	YemMemberDTO dto = new YemMemberDTO();
    	
    	dto.setMemberCode(reqModel.getMemberCode());
    	dto.setMemberName(reqModel.getMemberName());
    	dto.setMobile(reqModel.getMobile());
    	dto.setOpenId(reqModel.getOpenId());
    	dto.setValid(reqModel.getValid());
    	dto.setPageNo(reqModel.getPageNo());
    	dto.setPageSize(reqModel.getPageSize());
    	dto.setCreateBy(reqModel.getCreateBy());
    	dto.setCreateTime(reqModel.getCreateTime());
    	dto.setUpdateBy(reqModel.getUpdateBy());
    	dto.setUpdateTime(reqModel.getUpdateTime());
    	
    	int total = memberService.getMemberListCount(dto);
    	
    	resp.setPageNo(reqModel.getPageNo());
    	resp.setPageSize(reqModel.getPageSize());
    	resp.setTotalPage(total);
    	
    	resp.setYemMembers(memberService.getMemberList(dto));
    	
    	return resp;
    }

    @PostMapping("/add")
    public BaseMsgResponse add(HttpServletRequest request) {
    	log.info("开始会员新增接口");
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	
    	AddYemMemberRequest reqModel = (AddYemMemberRequest) JSONObject.parseObject(param, 
    			AddYemMemberRequest.class);
    	
    	AddYemMemberResponse resp = new AddYemMemberResponse();

    	YemMember member = new YemMember();
    	member.setMemberCode(baseService.getMcCode(McCodeTypeEnum.MEMBER.toString()));
    	member.setMemberName(reqModel.getMemberName());
    	member.setMobile(reqModel.getMobile());
    	member.setCreateBy(null);
    	member.setCreateTime(DateUtil.getDate());
    	member.setUpdateBy(null);
    	member.setUpdateTime(DateUtil.getDate());
    	
    	boolean result = memberService.addMember(member);
    	
    	if (!result) {
        	
        	resp.setRespCode(Constants.RESP_BIZ_ERR_CODE);
        	//新增会员失败
        	resp.setRespMsg(Constants.MemberMsg.MEMBER_ADD_ERROR);
    	}
    	return resp;
    }


    @PostMapping("/modify")
    public BaseMsgResponse modify(HttpServletRequest request) {
    	log.info("开始会员修改接口");
    	
    	String param = request.getHeader(ApiConstant.ROUTE_KEY);
    	
    	ModifyYemMemberRequest reqModel = (ModifyYemMemberRequest) JSONObject.parseObject(param, 
    			ModifyYemMemberRequest.class);
    	
    	ModifyYemMemberResponse resp = new ModifyYemMemberResponse();

    	YemMember member = new YemMember();
    	member.setMemberName(reqModel.getMemberName());
    	member.setMobile(reqModel.getMobile());
    	member.setOpenId(reqModel.getOpenId());
    	member.setUpdateBy(null);
    	member.setUpdateTime(DateUtil.getDate());
    	
    	boolean result = memberService.addMember(member);
    	
    	if (!result) {
    		
        	resp.setRespCode(Constants.RESP_BIZ_ERR_CODE);
        	//新增会员失败
        	resp.setRespMsg(Constants.MemberMsg.MEMBER_MODIFY_ERROR);
    	}
    	return resp;
    }
}
