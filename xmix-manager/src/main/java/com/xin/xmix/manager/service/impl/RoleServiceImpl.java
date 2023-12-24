package com.xin.xmix.manager.service.impl;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.xmix.common.exception.*;
import com.xin.xmix.manager.dao.RoleDao;
import com.xin.xmix.manager.entity.Role;
import com.xin.xmix.manager.entity.RoleFile;
import com.xin.xmix.manager.service.RoleFileService;
import com.xin.xmix.manager.service.RoleService;
import com.xin.xmix.manager.vo.RoleLoginVo;
import com.xin.xmix.manager.vo.RoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service("RoleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

    private final RoleFileService roleFileService;
    RoleServiceImpl(RoleFileService roleFileService){
        this.roleFileService = roleFileService;
    }
    /**
     * 注册
     * @param roleLoginVo：用户提交的信息
     */
    @Transactional //开启事务
    @Override
    public RoleLoginVo Register(RoleLoginVo roleLoginVo) {
        Role role = new Role();
        String username = roleLoginVo.getUsername();
        if(getRoleByName(username) != null){
            throw new AcountRepeatException("用户名重复！");
        }
        String nickname = roleLoginVo.getNickname();
        role.setId(roleLoginVo.getId());
        role.setUsername(username);
        role.setPassword(roleLoginVo.getPassword());
        role.setNickname(nickname == null?username:nickname);
        role.setPrivilege(roleLoginVo.getPrivilege());
        role.setState(roleLoginVo.isState());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        role.setCreateTime(simpleDateFormat.format(new Date()));
        if(saveOrUpdate(role)) {
            BeanUtils.copyProperties(role,roleLoginVo);
            return roleLoginVo;
        }
        throw new AcountRepeatException();
    }

    /**
     * 登陆
     * @param roleLoginVo：用户提交的信息
     */
    @Override
    public RoleLoginVo Login(RoleLoginVo roleLoginVo) {
        if(roleLoginVo != null && roleLoginVo.isState()){
            String username = roleLoginVo.getUsername();
            String password = roleLoginVo.getPassword();
            Role roleByName = getRoleByName(username);
            RoleLoginVo roleInfo = new RoleLoginVo();
            String password1 = roleByName.getPassword();
            if(password.equals(password1)){
                //密码正确
                BeanUtils.copyProperties(roleByName,roleInfo);
                return roleInfo;
            }
        }
        throw new LoginNoPassException();
    }

    /**
     * 更新用户信息
     * @param roleVo ： 最新的用户信息
     */
    @Transactional  //开启事务
    @Override
    public void updateUser(RoleVo roleVo) {
        Role role = new Role();
        BeanUtils.copyProperties(roleVo,role);
        if(!this.updateById(role)){
            throw new UpdateFailedException();
        }
    }

    @Override
    public void deleteUser(Integer id) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        if(!this.remove(queryWrapper)){
            throw new DeleteFailedException();
        }
    }
    @Transactional
    @Override
    public void batchDeleteUserByIds(String ids) {
        List<Integer> userIds = getUserIdsFromString(ids);
        if(!this.removeBatchByIds(userIds)){
            throw new DeleteFailedException();
        }
    }

    @Override
    public void exportAll(HttpServletResponse response) throws IOException {
        //查询出所有的数据
        List<Role> list = this.list();
        ExcelWriter writer = null;
        ServletOutputStream out = null;
        try {
            //使用工具类将查询出的所有数据导出成excel
            writer = ExcelUtil.getWriter(true);
            writer.write(list,true);

            //设置浏览器的响应格式，让用户下载导出的数据
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            String fileName = URLEncoder.encode("用户信息","UTF-8");
            response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");

            //创建一个输出流
            out = response.getOutputStream();
            //将writer中的数据刷新到response的输出流中
            writer.flush(out,true);
        } catch (Exception e) {
            throw new ExportFailedException();
        } finally {
            out.close();
            writer.close();
        }
    }

    /**
     * 将String类型的userId转换成Integer[]类型的
     * @param ids ： "10-11"
     * @return Integer[]
     */
    public List<Integer> getUserIdsFromString(String ids){
        String[] userIds = ids.split("-");
        return Arrays.stream(userIds).map(Integer::parseInt).collect(Collectors.toList());
    }
    private Role getRoleByName(String username){
        QueryWrapper<Role> wrapper = new QueryWrapper<Role>().eq("username", username);
        List<Role> list = this.list(wrapper);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
