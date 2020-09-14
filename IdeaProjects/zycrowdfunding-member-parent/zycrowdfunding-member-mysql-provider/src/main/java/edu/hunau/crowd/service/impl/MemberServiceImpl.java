package edu.hunau.crowd.service.impl;

import edu.hunau.crowd.entity.po.MemberPO;
import edu.hunau.crowd.entity.po.MemberPOExample;
import edu.hunau.crowd.mapper.MemberPOMapper;
import edu.hunau.crowd.service.api.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {
    MemberPOMapper memberPOMapper;

    @Autowired
    public void setMemberPOMapper(MemberPOMapper memberPOMapper) {
        this.memberPOMapper = memberPOMapper;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    @Override
    public void saveMember(MemberPO memberPO) {
        memberPOMapper.insertSelective(memberPO);
    }

    @Override
    public MemberPO getMemberPOByLoginAcct(String loginAcct) {
        MemberPOExample memberPOExample = new MemberPOExample();
        MemberPOExample.Criteria criteria = memberPOExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);
        List<MemberPO> memberPOS = memberPOMapper.selectByExample(memberPOExample);
        if (memberPOS==null||memberPOS.size()==0){return null;}
        return memberPOS.get(0);
    }
}
