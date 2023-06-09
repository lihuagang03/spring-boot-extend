package com.spring.boot.mybatis.plus.example.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.boot.mybatis.plus.example.repository.entity.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link UserService}
 *
 * @author lihuagang
 * @date 2023/6/10
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    @Resource
    private UserService userService;

    /**
     * 跨测试方法透传
     */
    private static Long id = 1L;

    @Test
    @Order(10)
    void save() {
        User user = new User();
        user.setUserName("liXiaoHua");
        user.setAge(18);
        user.setMobile("13588886666");
        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now);
        user.setUpdateTime(now);

        boolean result = userService.save(user);
        assertThat(result).isTrue();
        assertThat(user.getId()).isGreaterThan(0L);

        id = user.getId();
    }

    @Test
    @Order(20)
    void getById() {
        User user = userService.getById(id);
        assertThat(user).isNotNull();
        assertThat(user.getAge()).isEqualTo(18);
    }

    @Test
    @Order(25)
    void lambdaQuery() {
        IPage<User> page = new Page<>(1, 10);
        Wrapper<User> queryWrapper = Wrappers.<User>lambdaQuery()
                .select(User::getId)
                .eq(User::getAge, 18);
        IPage<User> userPage = userService.page(page, queryWrapper);
        assertThat(userPage.getTotal()).isGreaterThan(0L);
        assertThat(userPage.getRecords().size()).isGreaterThan(0);

        queryWrapper = userService.lambdaQuery()
                .eq(User::getAge, 18)
                .getWrapper();
        userPage = userService.page(page, queryWrapper);
        assertThat(userPage.getTotal()).isGreaterThan(0L);
        assertThat(userPage.getRecords().size()).isGreaterThan(0);

        userPage = userService.lambdaQuery()
                .eq(User::getAge, 18)
                .page(page);
        assertThat(userPage.getTotal()).isGreaterThan(0L);
        assertThat(userPage.getRecords().size()).isGreaterThan(0);
    }

    @Test
    @Order(30)
    void lambdaUpdate() {
        // 模拟更新操作延时
        mockTimeDelay();

        LocalDateTime now = LocalDateTime.now();

        // 字段填充，生效
//        User user = new User();
//        user.setId(id);
//        user.setMobile("13566668888");
////        user.setUpdateTime(now);
//        boolean result = userService.updateById(user);
        // 字段填充，不生效
        boolean result = userService.lambdaUpdate()
                .set(User::getMobile, "13566668888")
                .set(User::getUpdateTime, now)
                .eq(User::getId, id)
                .update();
        assertThat(result).isTrue();

        User user = userService.getById(id);
        assertThat(user).isNotNull();
        assertThat(user.getUpdateTime()).isNotEqualTo(user.getCreateTime());
    }

    @Test
    @Order(40)
    void removeById() {
        boolean result = userService.removeById(id);
        assertThat(result).isTrue();

        User user = userService.getById(id);
        assertThat(user).isNull();
    }

    private static void mockTimeDelay() {
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
