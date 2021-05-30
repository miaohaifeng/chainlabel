package com.happy.video;

import com.happy.video.pojo.SysUser;
import com.happy.video.service.impl.SysUserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmallVideoApplicationTests {

	@Autowired
	private SysUserServiceImpl userService;


	@Test
	public void Test001() {
		SysUser user = userService.selectByPrimaryKey(1);
		System.out.println(user.toString());
	}


}

