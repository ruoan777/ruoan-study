package com.ruoan.study.snowflake;

import com.dangdang.ddframe.rdb.sharding.id.generator.self.CommonSelfIdGenerator;
import com.dangdang.ddframe.rdb.sharding.id.generator.self.IPIdGenerator;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年08月26日 20:34:00
 */
public class TestSnow {
    public static void main(String[] args) {

        IPIdGenerator ipIdGenerator= new IPIdGenerator();
        ipIdGenerator.generateId();
        CommonSelfIdGenerator idGenerator = new CommonSelfIdGenerator();
        Number number = idGenerator.generateId();
        System.out.println(number);
    }
}
