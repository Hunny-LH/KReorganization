package com.github.lh.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 17-12-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtToken implements AuthenticationToken {

    private static final long serialVersionUID = -1608479718747727898L;
    /**
     * jwt
     */
    private String jwt;
    /**
     * host
     */
    private String host;

    @Override
    public Object getPrincipal() {
        return this.jwt;
    }

    @Override
    public Object getCredentials() {
        return Boolean.TRUE;
    }
}
