package top.hanjie.interceptor;

import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;

import java.util.Properties;

/**
 * 数据权限拦截器
 *
 * @author 黄汉杰
 */
@Slf4j
//@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class})})
public class PermissionInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        // 1.获取 StatementHandler ，默认是 RoutingStatementHandler
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        // 2.获取 StatementHandler 包装类
        MetaObject metaObjectHandler = SystemMetaObject.forObject(statementHandler);
        // 3.获取查询接口映射的相关信息
        MappedStatement mappedStatement = (MappedStatement) metaObjectHandler.getValue("delegate.mappedStatement");
        // 4.获取请求时的参数
        Object parameterObject = statementHandler.getParameterHandler().getParameterObject();
        // 5.获取 sql
        String sql = mappedStatement.getBoundSql(parameterObject).getSql();
        // 6.获取执行 sql 方法（这里可以搞个拓展啥的，限定什么方法才做数据权限）
        String sqlId = mappedStatement.getId();

        Statement parse = CCJSqlParserUtil.parse(sql);
        Select select = (Select) parse;
        SelectBody selectBody = select.getSelectBody();

        PlainSelect plainSelect = (PlainSelect) selectBody;


        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        metaObject.setValue("delegate.boundSql.sql", sql);

        // 调用原方法
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

}