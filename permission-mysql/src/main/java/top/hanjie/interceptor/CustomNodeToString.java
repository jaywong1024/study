package top.hanjie.interceptor;

import com.foundationdb.sql.StandardException;
import com.foundationdb.sql.parser.FromBaseTable;
import com.foundationdb.sql.parser.SQLParser;
import com.foundationdb.sql.parser.StatementNode;
import com.foundationdb.sql.unparser.NodeToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 覆写 node to string.
 *
 * @author 黄汉杰
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class CustomNodeToString extends NodeToString {

    private String replaceTableName;
    private String conditionExpression;

    @Override
    protected String fromBaseTable(FromBaseTable node) throws StandardException {
//        String tn = "数据库类型." + toString(node.getOrigTableName());
        String n = node.getCorrelationName();

        String origTableName = toString(node.getOrigTableName());
        if (Objects.equals(origTableName, replaceTableName)) {
            origTableName = conditionExpression;
        }
        if (n == null) {
            return origTableName;
        } else {
            return origTableName + " AS " + n;
        }


    }

    @SneakyThrows
    private static void test(String sql, String replaceTableName, String conditionExpression) {
        CustomNodeToString customNodeToString = new CustomNodeToString(replaceTableName, conditionExpression);
        // 解析器
        SQLParser parser = new SQLParser();
        // 语句
        StatementNode stmt = parser.parseStatement(sql);
        // 转译
        sql = customNodeToString.toString(stmt);
        // 结果
        System.out.println(sql);
    }

    public static void main(String[] args) {
        test(
                "SELECT * FROM (select * from test where id = '老铁666') t1 " +
                "left join (select * from test) t2 on t1.id = t2.id",
                "test",
                "(select * from test where id = '哈哈哈哈哈')"
        );
        test(
                "SELECT fd_id as id FROM ecom2g.test t1 left join ecom2g.hahaha t1 on t1.fd_id = t2.fd_id",
                "ecom2g.test",
                "(select * from ecom2g.test666 where id = '哈哈哈哈哈')"
        );
        test(
                "(select * from test) union (select * from test where id in (select id from test))",
                "test",
                "(select * from test where id = '哈哈哈哈哈')"
        );

    }

}