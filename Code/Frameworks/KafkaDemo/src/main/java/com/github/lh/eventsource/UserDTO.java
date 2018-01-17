package com.github.lh.eventsource;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 17-12-29
 */
@Data
@Builder
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -6015220413270925308L;
    private Long id;
    private String username;
    private String password;
}
