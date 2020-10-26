package com.kexie.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kexie.entity.Student;
import com.kexie.service.impl.StudentServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Resource
 * @author
 */
public class WebLoginShiroRealm extends AuthorizingRealm {

    private static Log log = LogFactory.getLog(WebLoginShiroRealm.class);

    @Autowired
    private StudentServiceImpl studentService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		/*UserInfo userInfo = (UserInfo)principalCollection.getPrimaryPrincipal();
		List<RoleInfo> roleInfoList = roleInfoService.getRoleInfoListByUserId(userInfo.getId());
		if (roleInfoList != null && roleInfoList.size() != 0){
			for (RoleInfo r : roleInfoList){
				authorizationInfo.addRole(r.getRoleName());
                List<MenuInfo> permissionInfoList = menuService.getPermissionInfoListByRoleId(r.getId());
				if (permissionInfoList != null && permissionInfoList.size() != 0){
					for (MenuInfo p : permissionInfoList){
						authorizationInfo.addStringPermission(p.getMenuContent());
					}
				}
			}
		}*/
        return authorizationInfo;
    }

    //主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        System.out.println( username);
        queryWrapper.eq("studentId",username);
        Student user = studentService.getOne(queryWrapper);
        if (user == null) {
            throw new UnknownAccountException(); // 账号不存在
        }
        if (user.getStatu() != 1) {
            throw new LockedAccountException();  // 账号被锁定
        }
        System.out.println(user);
        setSession("userInfo", user);
        //查询登陆账号密码
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getStudentId()), getName());
        return authenticationInfo;
    }
    /**
     * �����¼��
     */
    @SuppressWarnings("unused")
    private void setSession(Object key, Object value){
        Session session = getSession();
        if(null != session){
            session.setAttribute(key, value);
        }else{
            log.error("��ȡhttpServletSessionʧ�ܣ�");
        }
    }

    public Session getSession(){
        try{
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null){
                session = subject.getSession();
            }
            if (session != null){
                return session;
            }
        }catch (InvalidSessionException e){
            log.error(e + "shiro Ȩ����֤���HttpServletSessionʧ�ܣ�");
        }
        return null;
    }

}