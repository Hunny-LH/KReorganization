package com.github.lh.authentication;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 17-12-25
 */
public class StatelessSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {
        // 不创建session
//        if (context.getAuthenticationToken() instanceof JwtToken) {
//            context.setSessionCreationEnabled(false);
//        }
        return super.createSubject(context);
    }
}
