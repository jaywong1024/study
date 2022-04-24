package top.hanjie.interceptor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;

/**
 * 数据权限拦截器
 *
 * @author 黄汉杰
 */
@Slf4j
@Component
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class PermissionInterceptor1 implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        // 1.获取原始 sql 语句
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs()[1];
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        String oldSql = boundSql.getSql();

        // 2.改变 sql 语句
        BoundSql newBoundSql = new BoundSql(
                mappedStatement.getConfiguration(),
                permission(oldSql),
                boundSql.getParameterMappings(),
                boundSql.getParameterObject()
        );
        MappedStatement newMs = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));
        invocation.getArgs()[0] = newMs;

        // 3.继续执行
        return invocation.proceed();
    }

    @SneakyThrows
    public static String permission(String sql) {
        Statement parse = CCJSqlParserUtil.parse(sql);
        Select select = (Select) parse;
        SelectBody selectBody = select.getSelectBody();

        if (selectBody instanceof SetOperationList) {
            SetOperationList setOperationList = (SetOperationList) selectBody;

            MetaObject metaObject = SystemMetaObject.forObject(setOperationList);

            List<SelectBody> selects = setOperationList.getSelects();
            selects.forEach(sel -> {
                PlainSelect plainSelect = (PlainSelect) sel;
                FromItem fromItem = plainSelect.getFromItem();
                System.out.println(1);
            });
        } else {
            PlainSelect plainSelect = (PlainSelect) selectBody;
            FromItem fromItem = plainSelect.getFromItem();

        }
        return sql;
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
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource,
                ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null) {
            for (String keyProperty : ms.getKeyProperties()) {
                builder.keyProperty(keyProperty);
            }
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.cache(ms.getCache());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }


}