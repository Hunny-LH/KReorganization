package com.github.lh.authentication;

import com.github.lh.dao.UserRepo;
import com.github.lh.domain.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Objects;

/**
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 17-12-25
 */
public class UserRealm extends AuthorizingRealm {

    private UserRepo userRepo;

    public UserRealm(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return new SimpleAuthorizationInfo();
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UpcToken authToken = (UpcToken) token;
        User user = userRepo.getUserByUsername(authToken.getUsername());
        if (user == null) {
            throw new AuthenticationException("User not exist");
        }
        if (Objects.equals(user.getPassword(), authToken.getPwd())) {
            throw new AuthenticationException("Password error");
        }
        user.setPassword(null);
        return new SimpleAuthenticationInfo(user, authToken.getCredentials(), getName());
    }
}
