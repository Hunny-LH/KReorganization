package com.github.lh.authentication;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 17-12-25
 */
@NoArgsConstructor
@AllArgsConstructor
public class UpcToken extends UsernamePasswordToken {
    private static final long serialVersionUID = -1760529373472760893L;
    /**
     * 验证码
     */
    private String captcha;

    public String getPwd() {
        return String.valueOf(getPassword());
    }

    @Override
    public Object getPrincipal() {
        return super.getPrincipal();
    }

    @Override
    public Object getCredentials() {
        return super.getCredentials();
    }
}
