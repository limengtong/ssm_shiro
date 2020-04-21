package com.sys.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sys.pojo.Notice;
import com.sys.service.NoticeService;



/**
* FileName: OtherController.java
* 访问后台首页
*/
@Controller
public class OtherController {
	protected final static Logger logger = LogManager.getLogger(OtherController.class);
	
	private final Long PAGESIZE = 10L;
	private final Long CURR = 1L;
	@Resource
	private NoticeService noticeService;
	
	/**
	 * 首页访问
	 */
	@RequestMapping("/")
	public ModelAndView index(){
		return new ModelAndView("index");
	}
	
	/**
	 * 首页访问
	 */
	@RequestMapping("/login")
	public ModelAndView home(){
		return new ModelAndView("login");
	}	
	
	/**
	 * 首页欢迎页面    
	 * @return 跳转到welcome.do
	 */
	@RequestMapping("welcome.do")
	public ModelAndView welcome(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("welcome");
		Long currPage = (CURR-1) * PAGESIZE;
		Long count = noticeService.selectCount();
		@SuppressWarnings("unused")
		Long totalPage = 0L;
		if(count>0){
			totalPage = count% PAGESIZE==0?count/PAGESIZE:(count/PAGESIZE)+1;
		}
		List<Notice> noticeList = noticeService.selectByPage(currPage, PAGESIZE);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			for (Notice notice : noticeList) {
				Date date = sdf1.parse(notice.getCreateTime());
				notice.setCreateTime(sdf2.format(date));
				mv.addObject("announcements",noticeList);
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return mv;
	}
	
	/**
	 * 显示公告
	 */
	@RequestMapping("/showAnnouncement.do")
	public void showAnnouncement(){
		
	}	
}
