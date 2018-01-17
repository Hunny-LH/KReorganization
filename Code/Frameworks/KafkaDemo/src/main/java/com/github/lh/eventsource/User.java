package com.github.lh.eventsource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户实体
 *
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 17-12-29
 */
@Slf4j
@NoArgsConstructor
@ToString(of = {"id", "username", "password", "createTime", "lastUpdateTime"})
public class User implements Serializable {
    private static final long serialVersionUID = 2257331386381227162L;
    @Getter
    private Long id;
    @Getter
    private String username;
    @Getter
    private String password;
    @Getter
    private LocalDateTime createTime;
    @Getter
    private LocalDateTime lastUpdateTime;

    public User handleEvent(UserDomainEvent event) {
        switch (event.getType()) {
            case CREATE:
                return handleCreateEvent(event);
            case UPDATE:
                return handleUpdateEvent(event);
            case DELETE:
                return handleDeleteEvent(event);
            default:
                return this;
        }
    }

    /**
     * 应用创建事件
     *
     * @param event
     * @return
     */
    public User handleCreateEvent(UserDomainEvent event) {
        log.info("Handle Create Event: {}", event);
        this.id = event.getPayload().getId();
        this.username = event.getPayload().getUsername();
        this.password = event.getPayload().getPassword();
        this.createTime = LocalDateTime.now();
        return this;
    }

    /**
     * 应用更新事件
     *
     * @param event
     * @return
     */
    public User handleUpdateEvent(UserDomainEvent event) {
        log.info("Handle Update Event: {}", event);
        if (event.getPayload().getUsername() != null) {
            this.username = event.getPayload().getUsername();
        }
        if (event.getPayload().getPassword() != null) {
            this.password = event.getPayload().getPassword();
        }
        this.lastUpdateTime = LocalDateTime.now();
        return this;
    }

    /**
     * 应用删除事件
     *
     * @param event
     * @return
     */
    public User handleDeleteEvent(UserDomainEvent event) {
        log.info("Handle Delete Event: {}", event);
        return null;
    }
}
