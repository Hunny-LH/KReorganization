package com.github.lh.eventsource;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 领域事件类型
 *
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 17-12-29
 */
@Data
@Builder
public class UserDomainEvent implements Serializable {
    private static final long serialVersionUID = 1418334172059614933L;
    /**
     * 事件类型
     */
    private EventType type;
    /**
     * 事件承载对象，这里应该是对应的数据转换对象
     */
    private UserDTO payload;
    /**
     * 事件的发生时间
     */
    private long eventTime;
}
