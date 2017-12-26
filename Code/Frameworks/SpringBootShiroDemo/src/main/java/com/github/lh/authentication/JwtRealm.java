package com.github.lh.authentication;

import com.github.lh.common.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 17-12-25
 */
@Slf4j
public class JwtRealm extends AuthorizingRealm {

    /**
     * 支持的token类型
     *
     * @return
     */
    @Override
    public Class getAuthenticationTokenClass() {
        return JwtToken.class;
    }

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // TODO: 17-12-25 设置角色权限 
        return info;
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
        String jwt = ((JwtToken) token).getJwt();
        try {
            // TODO: 17-12-25 jwt校验
            Claims claimsJws = JwtUtils.parseJWT(jwt);
            return new SimpleAuthenticationInfo(claimsJws, true, getName());
        } catch (Exception e) {
            throw new AuthenticationException("JWT 令牌错误:" + e.getMessage());
        }
    }
}
