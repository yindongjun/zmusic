package com.example.zmusic.hutool;

import cn.hutool.core.net.NetUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class NetUtilTests {
  @Test
  void test_getLocalHostName() {
    String localHostName = NetUtil.getLocalHostName();
    log.info(localHostName);
  }

  @Test
  void test_getLocalHostStr() {
    String localhostStr = NetUtil.getLocalhostStr();
    log.info(localhostStr);
  }
}
