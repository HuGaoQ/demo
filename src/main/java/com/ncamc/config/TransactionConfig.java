package com.ncamc.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 * 事务配置
 * JtaTransactionManager：适用于需要跨多个资源（如多个数据库、消息队列等）的事务管理
 */
@Configurable
@EnableTransactionManagement // 启用事务管理功能，Spring AOP 会为标注为 @Transactional 的方法自动添加事务管理逻辑
public class TransactionConfig {

    /**
     * 1.DataSourceTransactionManager:
     *  参数：DataSource dataSource
     *  用途：用于管理单个数据源的事务，适用于基于 JDBC 的应用程序。
     *  特点：
     *      1.适用于简单的 JDBC 操作。
     *      2.不支持分布式事务。
     * 2.JpaTransactionManager
     *  参数：EntityManagerFactory emf
     *  用途：用于管理 JPA（Java Persistence API）的事务。
     *  特点：
     *      1.适用于使用 JPA 或 Hibernate 的应用程序。
     *      2.支持 JPA 的持久化上下文（Persistence Context）。
     * 3.HibernateTransactionManager
     *  参数：SessionFactory sessionFactory
     *  用于管理 Hibernate 的事务。
     *  特点：
     *      1.适用于直接使用 Hibernate 的应用程序。
     *      2.支持 Hibernate 的 Session 管理。
     * 4.JtaTransactionManager
     *  参数：
     *  用途：用于管理分布式事务，支持 JTA（Java Transaction API）。
     *  特点：
     *      1.适用于需要跨多个资源（如多个数据库、消息队列等）的事务管理。
     *      2.依赖于 JTA 实现（如 Atomikos、Bitronix 或应用服务器提供的 JTA）。
     * 5.WebLogicJtaTransactionManager
     *  参数：
     *  用途：专用于 WebLogic 服务器的 JTA 事务管理器。
     *  特点：
     *      1.适用于在 WebLogic 服务器上运行的应用程序。
     *      2.支持 WebLogic 的 JTA 实现。
     * 6.WebSphereUowTransactionManager
     *  参数：
     *  用途：专用于 WebSphere 服务器的 JTA 事务管理器。
     *  特点：
     *      1.适用于在 WebSphere 服务器上运行的应用程序。
     *      2.支持 WebSphere 的 JTA 实现。
     * 7.CciLocalTransactionManager
     *  参数：ConnectionFactory connectionFactory
     * 用途：用于管理 CCI（Common Client Interface）的本地事务。
     * 特点：
     *      1.适用于基于 JCA（Java Connector Architecture）的应用程序。
     *      2.不支持分布式事务。
     * 8.ReactiveTransactionManager（Spring 5.2+）
     *  参数：ConnectionFactory connectionFactory
     *  用途：用于管理响应式编程模型中的事务。
     *  特点：
     *      1.适用于响应式应用程序（如使用 Spring WebFlux 和 R2DBC）。
     *      2.支持响应式事务管理。
     * 9.ChainedTransactionManager（已弃用）
     *  参数：PlatformTransactionManager tm1, PlatformTransactionManager tm2
     *  用途：用于管理多个事务管理器的链式事务。
     *  特点：
     *      1.适用于需要同时管理多个资源的事务。
     *      2.已弃用，推荐使用 JTA 或分布式事务解决方案。
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        // 可根据需要选择适当的事务管理器
        return new JtaTransactionManager();
    }

}
