package com.sys.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sys.pojo.Notice;
import com.sys.service.NoticeService;

/**
 * 公告管理控制层
 * @author Administrator
 *
 */
@RequestMapping("notice")
@Controller
public class NoticeController {
	protected final static Logger logger = LogManager.getLogger(NoticeController.class);
	protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
	
	@Resource
	private NoticeService noticeService;
	
	/**
	 * 公告列表
	 * @param request
	 * @param curr
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("list.do")
	public String toList(HttpServletRequest request,
						@RequestParam(value="currPage",required=false) Long curr,
						@RequestParam(value="pageSize",required=false) Long pageSize){
		if(curr==null){
			curr=1L;
		}
		if(pageSize==null){
			pageSize=10L;
		}
		Long currPage = (curr-1)*pageSize;
		Long count = noticeService.selectCount();
		Long totalPage = 0L;
		if(count>0){
			totalPage = count%pageSize==0?count/pageSize:(count/pageSize)+1;
		}
		List<Notice> noticeList = noticeService.selectByPage(currPage, pageSize);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("count", count);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("currPage", curr);
		request.setAttribute("pageSize", pageSize);
		return "notice/list";
	}
	
	@RequestMapping("delete.do")
	public String delete(@RequestParam(value="id",required=false) Long id,RedirectAttributes attr){
		try{
			noticeService.delete(id);
			return "redirect:list.do";
		}catch(Exception e){
			logger.debug(e);
			attr.addFlashAttribute("message", "删除失败！");
			return "redirect:list.do";
		}
		
	}
	/**
	* @Description: 添加公告页面-跳转
	* @return
	* @throws  
	* @author lmt
	* @Date 2020年4月4日下午2:10:15
	* @version 1.00
	 */
	@RequestMapping("noticeAdd")
	public String goAdd(HttpServletRequest request){
		return "notice/add";
	}
	
	/**
	* @Description: 添加公告时验证是否已存在
	* @param request
	* @param attr
	* @param account
	* @return
	* @throws  
	* @author lmt
	* @Date 2020年4月4日下午3:37:01
	* @version 1.00
	 */
	@RequestMapping(value="checkNotice.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkUserName(HttpServletRequest request,RedirectAttributes attr,
							@RequestParam(value="account",required=false) String account){
			Notice notice = new Notice(account);
			Notice au = noticeService.selectOne(notice);
			if(null==au){	
				resultMap.put("status", 200);
				resultMap.put("message", true);
			}else{
				resultMap.put("status", 204);
				resultMap.put("message", false);
			}
			return resultMap;	
	}
	
	/**
	* @Description: 添加公告-提交
	* @param request
	* @param attr
	* @param account
	* @param password
	* @param checkPID
	* @return
	* @throws  
	* @author lmt
	* @Date 2020年4月4日下午5:56:48
	* @version 1.00
	 */
	@RequestMapping(value="noticeAdd.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doAdd(HttpServletRequest request,RedirectAttributes attr,
						@RequestParam(value="account",required=false) String account){
		try{
			Notice notice = new Notice(account);	
			Notice au = noticeService.selectisDelete(notice);
			if (null == au) {
				Notice pojo = new Notice();
				pojo.setAccount(account);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				pojo.setCreateTime(sdf.format(new Date()));
				pojo.setIsDisabled(false);
				int res = noticeService.insert(pojo);
				logger.debug("添加结果============" + res);
			} else {
				noticeService.updateDisDelete(au.getId(), false);
			}
			resultMap.put("status", 200);
			resultMap.put("message", "添加成功！");
		}catch(Exception e){
			logger.debug(e);
			resultMap.put("status", 500);
			resultMap.put("message", "添加失败！");
		}
		return resultMap;	
	}
	
	
	/**
	* @Description: 禁用/启用
	* @param request
	* @param attr
	* @param userID
	* @return
	* @throws  
	* @author lmt
	* @Date 2020年4月4日上午11:25:56
	* @version 1.00
	 */
	@RequestMapping("updateDisabled.do")
	public String updateDisabled(HttpServletRequest request,RedirectAttributes attr,
								@RequestParam(value="id") Long userID){
		try{
			Notice au = noticeService.selectOne(userID);
			if(au.getIsDisabled()){
				noticeService.updateDisabled(userID, false);
			}else{
				noticeService.updateDisabled(userID, true);
			}
		}catch(Exception e){
			logger.debug(e);
			attr.addFlashAttribute("message", "操作失败！");
			return "redirect:list.do";
		}
		return "redirect:list.do";
	}
	

}
