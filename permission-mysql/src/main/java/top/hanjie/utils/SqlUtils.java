package top.hanjie.utils;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.foundationdb.sql.StandardException;
import com.foundationdb.sql.parser.FromBaseTable;
import com.foundationdb.sql.parser.SQLParser;
import com.foundationdb.sql.parser.StatementNode;
import com.foundationdb.sql.parser.UnionNode;
import com.foundationdb.sql.unparser.NodeToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.Select;

import java.util.Objects;

/**
 * Sql 工具类.
 *
 * @author 黄汉杰
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class SqlUtils extends NodeToString {

    /**
     * 替换源
     */
    private String replaceSource;
    /**
     * 替换值
     */
    private String replaceValue;

    /**
     * 覆写 fromBaseTable，进行表名替换
     * @author 黄汉杰
     * @date 2022/4/25 0025 11:25
     * @param node   节点
     * @return java.lang.String
     */
    @Override
    protected String fromBaseTable(FromBaseTable node) throws StandardException {
        // 1.获取表名
        String origTableName = toString(node.getOrigTableName());
        // 2.获取别名
        String correlationName = node.getCorrelationName();
        // 3.如果表名为需要替换的则进行替换
        if (Objects.equals(origTableName, replaceSource)) {
            origTableName = replaceValue;
        }
        // 4.判断如果别名存在则返回别名
        if (StrUtil.isBlank(correlationName)) {
            return origTableName + " AS DP" + RandomUtil.randomNumbers(5);
        } else {
            return origTableName + " AS " + correlationName;
        }
    }

    /**
     * 处理 union all 被转译为 union
     * @author 黄汉杰
     * @date 2022/4/25 0025 15:11
     * @param node   节点
     * @return java.lang.String
     */
    @Override
    protected String unionNode(UnionNode node) throws StandardException {
        if (node.isAll()) {
            return this.toString(node.getLeftResultSet()) + " UNION ALL " + this.toString(node.getRightResultSet());
        }
        return super.unionNode(node);
    }

    /**
     * 替换表
     *
     * @param sql sql
     * @param rs  替换源
     * @param rv  替换值
     * @return java.lang.String
     * @author 黄汉杰
     * @date 2022/4/25 0025 11:24
     */
    @SneakyThrows
    public static String replaceTable(String sql, String rs, String rv) {
        // 1.校验
        if (StrUtil.isBlank(rs) || StrUtil.isBlank(rv)) {
            return sql;
        }
        // 2.获取格式化好的 sql
        String format = ((Select) CCJSqlParserUtil.parse(sql)).getSelectBody().toString();
        // 3.构建语句
        StatementNode stmt = new SQLParser().parseStatement(format);
        // 4.转译返回
        return new SqlUtils(rs, rv).toString(stmt);
    }

}