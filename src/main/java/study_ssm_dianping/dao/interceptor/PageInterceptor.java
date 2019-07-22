package study_ssm_dianping.dao.interceptor;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import study_ssm_dianping.bean.BaseBean;
import study_ssm_dianping.bean.Page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Create By yuanyuan on 2019/7/20 9:30
 */
@Intercepts({@Signature(type = StatementHandler.class,method = "prepare",args = {Connection.class})})
public class PageInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        String id = mappedStatement.getId();
        if (id.endsWith("ByPage")) {
            BoundSql boundSql = statementHandler.getBoundSql();
            String sql = boundSql.getSql();
            String countSql = "select count(*) from (" + sql + ")t";
            Connection connection = (Connection) invocation.getArgs()[0];
            PreparedStatement statement = connection.prepareStatement(countSql);
            ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
            parameterHandler.setParameters(statement);
            ResultSet rs = statement.executeQuery();
            BaseBean baseBean = (BaseBean) boundSql.getParameterObject();
            Page page = baseBean.getPage();
            if (rs.next()) {
                page.setTotalNumber(rs.getInt(1));
            }
            String pageSql = sql + " limit " + (page.getCurrentPage() - 1) * page.getPageNumber() + "," + page.getPageNumber();
            metaObject.setValue("delegate.boundSql.sql", pageSql);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
