package com.user.domeuser.aspect;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.util.Map;
import java.util.Properties;


/**
 * @author littlenew
 * @date 2020/5/14 4:36 PM
 **/
@Intercepts({
//        @Signature(
//                type = Executor.class,
//                method = "query",
//                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
//        ),
//        @Signature(
//                type = Executor.class,
//                method = "update",
//                args = {MappedStatement.class, Object.class}
//        ),
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})

public class MybatisInterceptor implements Interceptor {
    private static Logger log = LoggerFactory.getLogger(MybatisInterceptor.class);
    /**
     * {表名:数量,表名:数量}
     */
    private static final String TABLES = "user_info:4";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // TODO: 自定义拦截逻辑
        Object target = invocation.getTarget();
        StatementHandler statementHandler = (StatementHandler) target;
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();
        log.info("原始sql：" + sql);

        resetSql(invocation);
        String sql1 = boundSql.getSql();
        log.info("替换后" + sql1);
        return invocation.proceed();

    }

    private void resetSql(Invocation invocation) {
        final Object target = invocation.getTarget();
        StatementHandler statementHandler = (StatementHandler) target;
        BoundSql boundSql = statementHandler.getBoundSql();
        if (StringUtils.isNotEmpty(boundSql.getSql())) {
            modify(boundSql, "sql", boundSql);
        }
    }

    private static void modify(Object object, String fieldName, BoundSql boundSql) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            Map<String, Object> map = (Map<String, Object>) boundSql.getParameterObject();
            String[] tables = TABLES.split(",");
            Long num = null;
            String sqlTable = null;
            for (String table : tables) {
                String[] t = table.split(":");
                if (boundSql.getSql().indexOf(t[0]) != -1) {
                    if (map.get("id") != null) {
                        num = Long.valueOf(map.get("id").toString()) % Long.valueOf(t[1]);
                        sqlTable = boundSql.getSql().replace(t[0], t[0] + "_" + num);

                    } else {
                        Integer tablenum = Integer.valueOf(t[1]);
                        String sql = boundSql.getSql();
                        StringBuffer sqlBuffer = new StringBuffer();
                        for (int i = 0; i < tablenum; i++) {
                            sqlBuffer.append(sql.replace(t[0], t[0] + "_" + i));
                            sqlBuffer.append("UNION");
                        }
                        sqlTable = sqlBuffer.toString();
                        sqlTable = sqlTable.substring(0, sqlTable.length() - 5);

                    }
                    field.set(object, sqlTable);

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object plugin(Object target) {
        // 返回代理类
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}


