package edu.hunau.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.hunau.crowd.constant.CrowdConstant;
import edu.hunau.crowd.entity.Admin;
import edu.hunau.crowd.entity.AdminExample;
import edu.hunau.crowd.entity.AdminExample.Criteria;
import edu.hunau.crowd.exception.LoginAcctAlreadyInUseException;
import edu.hunau.crowd.exception.LoginAcctAlreadyInUseForUpdateException;
import edu.hunau.crowd.exception.LoginFailedException;
import edu.hunau.crowd.mapper.AdminMapper;
import edu.hunau.crowd.service.api.AdminService;
import edu.hunau.crowd.util.CrowdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminMapper adminMapper;

    @Autowired
    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void saveAdmin(Admin admin) {
        String loginAcct = admin.getLoginAcct();
        if (loginAcct == null || loginAcct == "") {
            throw new LoginAcctAlreadyInUseException("账号不能为空");
        }
        // 1.密码加密
        String userPswd = admin.getUserPswd();
        if (userPswd == null || userPswd == "") {
            throw new LoginAcctAlreadyInUseException("密码不能为空");
        }
        //userPswd = CrowdUtil.md5Encrypt(userPswd);
        userPswd=passwordEncoder.encode(userPswd);
        admin.setUserPswd(userPswd);

        // 2.生成创建时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = format.format(date);
        admin.setCreateTime(createTime);

        // 3.执行保存
        try {
            adminMapper.insert(admin);
        } catch (Exception e) {
            e.printStackTrace();

            logger.info("异常全类名=" + e.getClass().getName());

            if (e instanceof DuplicateKeyException) {
                throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }
    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public Admin getAdminByLoginAccount(String loginAccount, String password) {
        if (loginAccount == null || loginAccount.length() == 0) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }
        AdminExample adminExample = new AdminExample();
        Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAccount);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (admins == null || admins.size() == 0) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        if (admins.size() > 1) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }
        Admin admin = admins.get(0);
        String getPassFromDB = admin.getUserPswd();
        String md5Encrypt = CrowdUtil.md5Encrypt(password);
        if (!Objects.equals(getPassFromDB, md5Encrypt)) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        return admin;
    }

    @Override
    public Admin getAdminByLoginAccount(String loginAccount) {
        AdminExample adminExample=new AdminExample();
        Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAccount);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        return admins.get(0);
    }

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> admins = adminMapper.selectAdminByKeyword(keyword);
        PageInfo<Admin> pageInfo = new PageInfo<>(admins);
        return pageInfo;
    }

    @Override
    public void removeAdminById(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }

    @Override
    public Admin getAdminById(Integer adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList) {
        // 1.根据 adminId 删除旧的关联关系数据
        adminMapper.deleteOldRelationship(adminId);
        // 2.根据 roleIdList 和 adminId 保存新的关联关系;
        if (roleIdList != null && roleIdList.size() > 0) {
            adminMapper.insertNewRelationship(adminId, roleIdList);
        }

    }

    @Override
    public void update(Admin admin) {
        // “Selective”表示有选择的更新，对于null值的字段不更新
        try {
            adminMapper.updateByPrimaryKeySelective(admin);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("异常全类名=" + e.getClass().getName());

            if (e instanceof DuplicateKeyException) {
                throw new LoginAcctAlreadyInUseForUpdateException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }
    }
}
