package com.github.lh.eventsource;

import lombok.extern.slf4j.Slf4j;

import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 17-12-29
 */
@Slf4j
public class MainTest {

    /**
     * 使用领域模型来做crud
     *
     * @param args
     */
    public static void main(String[] args) {
        // 流程1
//        process1();

        // 本地流的处理流程
        localStreamProcess();
    }

    public static void localStreamProcess() {
        UserDomainEvent create = UserDomainEvent.builder()
                .type(EventType.CREATE)
                .payload(UserDTO.builder()
                        .id(1L)
                        .username("liuhan")
                        .password("liuhan")
                        .build())
                .build();

        UserDomainEvent update = UserDomainEvent.builder()
                .type(EventType.UPDATE)
                .payload(UserDTO.builder()
                        .password("newpassword")
                        .build())
                .build();

        UserDomainEvent delete = UserDomainEvent.builder()
                .type(EventType.DELETE)
                .build();

        UserDomainEvent update2 = UserDomainEvent.builder()
                .type(EventType.UPDATE)
                .payload(UserDTO.builder()
                        .password("newpassword2")
                        .build())
                .build();


        // 模拟一个事件流
        Stream<UserDomainEvent> stream = Stream.of(create, update, create, update2);

        // 最新的User = 对初始状态的User 应用所有的事件
        // 初始状态的User
        Supplier<UserHolader> firstStateUser = () -> new UserHolader(null);

        BiConsumer<UserHolader, UserDomainEvent> eventHandler = (userHolader, event) -> {
            User user = userHolader.getUser();
            if (user == null) {
                if (event.getType() == EventType.CREATE) {
                    userHolader.setUser(new User().handleEvent(event));
                }
            } else {
                userHolader.setUser(user.handleEvent(event));
            }
        };

        Collector<UserDomainEvent, UserHolader, User> collector = Collector.of(firstStateUser, eventHandler, (userHolader, userHolader2) -> userHolader2,
                UserHolader::getUser);

        User user = stream.collect(collector);
        printNowUser(user);
    }


    public static void process1() {
        // 创建用户，即发布一个创建的领域事件
        UserDomainEvent create = UserDomainEvent.builder()
                .type(EventType.CREATE)
                .payload(UserDTO.builder()
                        .id(1L)
                        .username("liuhan")
                        .password("liuhan")
                        .build())
                .build();
        User user = new User();
        user = user.handleCreateEvent(create);
        printNowUser(user);

        UserDomainEvent update = UserDomainEvent.builder()
                .type(EventType.UPDATE)
                .payload(UserDTO.builder()
                        .password("newpassword")
                        .build())
                .build();
        user = user.handleUpdateEvent(update);
        printNowUser(user);


        UserDomainEvent delete = UserDomainEvent.builder()
                .type(EventType.DELETE)
                .build();
        user = user.handleDeleteEvent(delete);
        printNowUser(user);
    }

    public static void printNowUser(User user) {
        if (user != null) {
            log.info("Now User is : {}", user);
        } else {
            log.info("Now User is deleted");
        }
    }
}

class UserHolader {
    private User user;

    public UserHolader(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public synchronized void setUser(User user) {
        this.user = user;
    }
}

