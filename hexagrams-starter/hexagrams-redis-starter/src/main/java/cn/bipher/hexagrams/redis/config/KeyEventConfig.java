package cn.bipher.hexagrams.redis.config;

import lombok.Getter;
import lombok.Setter;

/**
 * redis key event common config
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
@Getter
@Setter
public class KeyEventConfig {

	private Boolean enabled = false;

}
