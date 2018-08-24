package com.lemon.smartwebframework.plugin.orm.session;

import com.lemon.smartwebframework.plugin.orm.config.Configuration;


public class SqlSessionFactoryImpl implements ISqlSessionFactory {

	private Configuration config;
	
	public SqlSessionFactoryImpl(Configuration config) {
		this.config = config;
	}

	@Override
	public ISqlSession openSession() {
		SqlSessionImpl sqlSession=new SqlSessionImpl(config);
		return sqlSession;
	}

}
