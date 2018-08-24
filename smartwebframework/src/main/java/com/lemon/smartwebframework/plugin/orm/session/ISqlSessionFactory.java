package com.lemon.smartwebframework.plugin.orm.session;

/**
 * Creates an {@link SqlSession} out of a connection or a DataSource
 * @author andy
 *
 */
public interface ISqlSessionFactory {
	ISqlSession openSession();
}
