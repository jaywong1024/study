package top.hanjie.interceptor;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import top.hanjie.entity.PermissionInfo;
import top.hanjie.utils.SqlUtils;
import top.hanjie.utils.ThreadLocalUtils;

import java.util.List;
import java.util.Properties;

/**
 * 数据权限拦截器
 *
 * @author 黄汉杰
 */
@Slf4j
@Component
@Intercepts({
        // 拦截 mybatis 的 Executor.query 方法
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        )
})
public class MybatisInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        // 1.获取原始 sql 语句
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        BoundSql boundSql = mappedStatement.getBoundSql(invocation.getArgs()[1]);
        String sql = boundSql.getSql();

        // 2.获取权限
        List<PermissionInfo> permissions = ThreadLocalUtils.get("permissions");

        // 3.改变 sql 语句
        if (CollectionUtil.isNotEmpty(permissions)) {
            for (PermissionInfo p : permissions) {
                sql = SqlUtils.replaceTable(sql, p.getTableName(), p.getConditionExpression());
            }
        }

        // 4.写回 MappedStatement
        BoundSql newBoundSql = new BoundSql(
                mappedStatement.getConfiguration(),
                sql,
                boundSql.getParameterMappings(),
                boundSql.getParameterObject()
        );
        invocation.getArgs()[0] = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));

        // 5.继续执行
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        // 把被拦截对象生成一个代理对象
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }


    /**
     * 绑定 sql 源
     */
    public static class BoundSqlSqlSource implements SqlSource {
        private final BoundSql boundSql;
        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }
        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            return this.boundSql;
        }
    }

    /**
     * 复制 MappedStatement
     *
     * @param ms           映射语句
     * @param newSqlSource 新的 sql 源
     * @return org.apache.ibatis.mapping.MappedStatement
     * @author 黄汉杰
     * @date 2022/4/24 0024 17:58
     */
    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        // 1.构造器
        MappedStatement.Builder builder = new MappedStatement.Builder(
                ms.getConfiguration(),
                ms.getId(),
                newSqlSource,
                ms.getSqlCommandType()
        );
        // 2.一系列构造操作...
        if (ms.getKeyProperties() != null) {
            for (String keyProperty : ms.getKeyProperties()) {
                builder.keyProperty(keyProperty);
            }
        }
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.cache(ms.getCache());
        builder.useCache(ms.isUseCache());
        // 3.返回结构
        return builder.build();
    }


}