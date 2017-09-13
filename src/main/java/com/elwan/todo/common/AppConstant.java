package com.elwan.todo.common;

public abstract class AppConstant {
	
	public abstract class DBType {
		public static final int MYSQL = 1;
		public static final int REDIS = 2;
		public static final int HIBERNATE = 3;
	}

	public abstract class Keys {
		public static final String DB_TYPE = "db.type";
		
		public static final String MYSQL_IP = "mysql.ip";
		public static final String MYSQL_PORT = "mysql.port";
		public static final String MYSQL_SERVICE = "mysql.service";
		public static final String MYSQL_USER = "mysql.user";
		public static final String MYSQL_PWD = "mysql.pwd";
		
		public static final String REDIS_IP = "redis.ip";
		public static final String REDIS_PORT = "redis.port";
		public static final String REDIS_PWD = "redis.pwd";
		
	}
	
	public abstract class Settings {
		public static final String CONFIG_FILE_PATH = "./config/config.properties";
		
	}
	
	public class Hibernate {
		public class Cache {
			public class Region {
				public static final String TODO = "todo";
			}	
		}
	}
	
	public class Redis {
		public class Keys {
			public static final String USER_ID = "USER_ID";
			public static final String TODO_ID = "TODO_ID";
		}
	}

}
