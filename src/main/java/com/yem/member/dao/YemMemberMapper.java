package com.yem.member.dao;

import java.util.List;

import com.yem.dto.YemMemberDTO;
import com.yem.entity.YemMember;

/**
 * 会员DAO
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2019年7月8日 上午11:10:04 <br/>
 *
 * @author <a href="mailto:hbszguchuang@163.com">guchuang</a>
 * @version 
 * @since JDK 1.8
 */
public interface YemMemberMapper {
    int deleteByPrimaryKey(String memberId);

    int insert(YemMember record);

    int insertSelective(YemMember record);

    YemMember selectByMemberCode(Long memberCode);

    int updateByMemberCodeSelective(YemMember record);

    int updateByPrimaryKey(YemMember record);
    
    /**
     * 分页查询会员信息
     * @author <a href="mailto:hbszguchuang@163.com">guchuang</a>
     * @param yemMemberDto
     * @return
     * @since JDK 1.8
     */
    List<YemMember> selectMemberList(YemMemberDTO yemMemberDto);
    
    /**
     * 分页查询会员数量
     * @author <a href="mailto:hbszguchuang@163.com">guchuang</a>
     * @param yemMemberDto
     * @return
     * @since JDK 1.8
     */
    int selectMemberListCount(YemMemberDTO yemMemberDto);
}