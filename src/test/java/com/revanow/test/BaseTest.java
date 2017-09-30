package com.revanow.test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.revanow.auth.dao.IRightDao;
import com.revanow.base.config.Application;
import com.revanow.base.config.JavaConf;
import com.revanow.test.dao.IAvIncomeDao;
import com.revanow.test.dao.IUpIncomeDao;
import com.revanow.test.entity.AvIncome;
import com.revanow.test.entity.UpIncome;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
@SpringBootTest(classes = {Application.class,JavaConf.class})
public class BaseTest {

	@Resource
	private IAvIncomeDao avIncomeDao;
	@Resource
	private IUpIncomeDao upIncomeDao;
	@Resource
	private IRightDao rightDao;
	
	@Test
	public void TestMid(){
		
		List<UpIncome> listAll = upIncomeDao.listUpIncomeAll();
	 	List<AvIncome> avList = avIncomeDao.listAvIncomeAll();
	 	
//	 	for (AvIncome item : avList) {
//			item.setMid(listAll.get(new Random().nextInt(listAll.size() - 1)).getMid());
//			avIncomeDao.update(item);
//		}
//		
		
	}
	
	
	public static void main(String[] args) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.WEEK_OF_YEAR, 20);
		
		System.out.println(DateFormatUtils.format(cal, "yyyy-MM-dd") + "   " + cal.getFirstDayOfWeek() + " " + cal.get(Calendar.DAY_OF_WEEK));
	}
}
