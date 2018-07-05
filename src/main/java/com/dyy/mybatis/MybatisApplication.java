package com.dyy.mybatis;

import com.dyy.mybatis.entity.MemoGroup;
import com.dyy.mybatis.mapper.MemoGroupMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.Date;

public class MybatisApplication {
    public static SqlSessionFactory sqlSessionFactory;
    public static void buildSqlSessionFactoryWithXml(){
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(
                    Resources.getResourceAsStream("mybatis-config.xml"));
            System.out.println(sqlSessionFactory);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        buildSqlSessionFactoryWithXml();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        MemoGroup memoGroup = new MemoGroup();
        memoGroup.setName("lxy");
        memoGroup.setCreatedTime(new Date());
        MemoGroupMapper memoGroupMapper = sqlSession.getMapper(MemoGroupMapper.class);
        int effect = memoGroupMapper.insertMemoGroup(memoGroup);
        System.out.println(effect);
        sqlSession.close();
    }

}
