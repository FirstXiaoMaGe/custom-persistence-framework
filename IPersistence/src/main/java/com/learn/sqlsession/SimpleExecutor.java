package com.learn.sqlsession;

import com.learn.config.BoundSql;
import com.learn.pojo.Configuration;
import com.learn.pojo.MappedStatement;
import com.learn.utils.GenericTokenParser;
import com.learn.utils.ParameterMapping;
import com.learn.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author liuxuke
 * @Title: SimpleExecutor
 * @ProjectName IPersistence_test
 * @Description: //TODO
 * @date 2020/6/19 15:46
 */
public class SimpleExecutor implements Executor {

    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        //1.注册驱动 获取数据库连接
        Connection connection = configuration.getDataSource().getConnection();
        // 2.获取sql语句  转换sql语句为能被jdbc能识别的语句，转换的过程中，还需要对#{}中的值进行解析存储
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        // 3.获取与处理对象 preparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());
        //4. 设置参数
        //获取到参数的全路径
        String paramterType = mappedStatement.getParamterType();
        Class<?> paramterTypeClass = getClassType(paramterType);
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        for (int i = 0; i < parameterMappings.size(); i++) {
            ParameterMapping parameterMapping = parameterMappings.get(i);
            String content = parameterMapping.getContent();
            //反射  根据参数名称获取实体属性中的值 然后根据参数获取到params中具体的值
            Field declaredField = paramterTypeClass.getDeclaredField(content);
            //防止对象是私有的，设置暴力访问
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);
            preparedStatement.setObject(i + 1, o);
        }
        //5.执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        String resultType = mappedStatement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType);
        ArrayList<Object> objects = new ArrayList<>();
        //6.封装返回结果集
        while (resultSet.next()) {
            Object o = resultTypeClass.newInstance();
            //取出元数据 （表）
            ResultSetMetaData metaData = resultSet.getMetaData();
            //获取到总列数 getColumnCount
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                //获取到字段名   columnName下表为1开始
                String columnName = metaData.getColumnName(i);
                // 获取到字段的值
                Object value = resultSet.getObject(columnName);
                // 使用反射或者内省 根据数据库表和实体的对应关系完成封装 并生成读写方法
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o, value);
            }
            objects.add(o);
        }
        return (List<E>) objects;
    }

    private Class<?> getClassType(String paramterType) throws ClassNotFoundException {
        if (paramterType != null) {
            Class<?> aClass = Class.forName(paramterType);
            return aClass;
        }
        return null;
    }

    /**
     * 完成对#{} 的解析
     * 1.讲#{}使用 ? 进行代替。
     * 2.解析出#{} 中的值进行存储
     *
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql) {
        //标记处理类：配置标记解析器来完成对占位符的解析处理工作
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        //解析后的sql
        String parseSql = genericTokenParser.parse(sql);
        //#{} 中解析出来的参数名称
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        BoundSql boundSql = new BoundSql(parseSql, parameterMappings);
        return boundSql;
    }
}
