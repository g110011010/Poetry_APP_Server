package com.gqf.constants;

import java.sql.Connection;

/*
* 用于存放项目中用到的常量*/
public  interface CONSTANTS {
    public static final String DB_USERNAME="yundb";
    public static final String DB_PASSWORD="1.aly2.yundb3.finish";
    public static final String DB_NAME="poetry";
    public static final String DB_HOST="39.107.232.16";
    public static final String DB_PORT="3306";

    public interface TABLE{
        public static final String ID="id";
        public static final String CREATED_AT="created_at";
        public static final String UPDATE_AT="update_at";

    }
/*数据库中诗词表常量*/
    public interface TABLE_POETRIES extends TABLE{

        public static final String POET_ID="poet_id";
        public static final String CONTENT="content";
        public static final String TITLE="title";
         }
    /*数据库诗人表常量*/
    public interface TABLE_POET extends TABLE{
        public static final String NAME="name";
    }
    public interface TABLE_POETRIES_L extends TABLE{
        public static final String TABLE_NAME="poetries_l";
        public static final String LONGTITUDE="longtitude";
        public static final String LATTITUDE="lattitude";
        public static final String CITY_OF_ANCIENT="city_of_ancient";
        public static final String CITY_OF_CURRENT="city_of_current";
        public static final String COUNTY_OF_ANCIENT="county_of_ancient";
        public static final String COUNTY_OF_CURRENT="county_of_current";
        public static final String DYNASTY="dynasty";
        public static final String AGE="age";
        public static final String TITLE="title";
        public static final String SEX="sex";
        public static final String POET_ID="poet_id";
    }
    public interface TABLE_POET_L extends TABLE{
        public static final String TABLE_NAME="poet_l";
        public static final String NAME="name";
    }

}
