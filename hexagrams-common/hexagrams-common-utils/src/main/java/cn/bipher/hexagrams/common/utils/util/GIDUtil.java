package cn.bipher.hexagrams.common.utils.util;

import cn.bipher.hexagrams.common.utils.contract.SeqNoEnumInterface;
import lombok.experimental.UtilityClass;

import java.util.UUID;

/**
 * id 生成器
 * @author Bipher
 * @version 1
 * @date 2023/6/26 23:52
 */
@UtilityClass
public class GIDUtil {

    /**
     * 获取UUID
     * @return UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取雪花ID
     * @return 雪花ID
     */
    public static Long getSID() {
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(genId(), genId());
        return snowflakeIdWorker.nextId();
    }

    /**
     * 拼接前缀
     *
     * @param seqNoEnum
     * @return sid
     */
    public static String getSID(SeqNoEnumInterface seqNoEnum) {
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(genId(), genId());
        return seqNoEnum.getCode() + snowflakeIdWorker.nextId();
    }


    private static Long genId() {
        String hostName = getUUID();
        byte[] ints     = StringUtil.utf8Bytes(hostName);
        int    sums     = 0;
        for (int i : ints) {
            sums += i;
        }
        return (long) (sums % 31);
    }
}