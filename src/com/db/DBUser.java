package com.db;

import android.provider.BaseColumns;

/**
 * ʵ����
 */
public final class DBUser {

	public static final class User implements BaseColumns {
		public static final String USERNAME = "username";
		public static final String PASSWORD = "password";
		public static final String ISSAVED = "issaved";
	}

}
