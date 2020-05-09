package com.alfred.system.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Alfred
 * @date 2020/4/28 17:46
 */
@ConfigurationProperties(prefix = "shiro")
@Data
public class ShiroProperties {

  /**
   *  哈希算法md5
   */
  private String hashAlgorithmName="md5";
  /**
   *  散列次数(2次)
   */
  private Integer hashIteration=2;
  /**
   * 登录地址
   */
  private String loginUrl;

  /**
   * 未经授权的网址
   */
  private String unauthorizedUrl;
  private String [] anonUrls;

  /**
   * 退出时的地址
   */
  private String  logoutUrl;
  /**认证网址
   *
   */
  private String [] authcUrls;
}
