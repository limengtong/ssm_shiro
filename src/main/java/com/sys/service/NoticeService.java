package com.sys.service;

import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sys.mapper.NoticeMapper;
import com.sys.pojo.Notice;

/**
 * 公告管理业务层
 * @author Administrator
 *
 */
@Service
public class NoticeService {
	@Resource
	NoticeMapper noticeMapper;
	Map<String,Long> map = new HashMap<String,Long>();

	public Long selectCount() {
		
		return noticeMapper.selectCount();
	}

	public List<Notice> selectByPage(Long currPage, Long pageSize) {
		map.put("currPage", currPage);
		map.put("pageSize", pageSize);
		return noticeMapper.selectByPage(map);
	}

	public int delete(Long id) {
		if(id==null){
			throw new RuntimeException("在删除数据的时候，没有指定id的值");
		}
		return noticeMapper.delete(id);
		
	}

	public Notice selectOne(Notice notice) {
		List<Notice> list = noticeMapper.select(notice);
		if(list!=null && list.size()>1){
			throw new RuntimeException("selectOne方法查询出了多条数据");
		}
		if(list==null || list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int insert(Notice pojo) {
		return noticeMapper.insert(pojo);
		
	}

	public Notice selectOne(Long userID) {
		Notice pojo = createInstanceAndSetIdOfFirstGeneric(userID);

        List<Notice> list = noticeMapper.select(pojo);
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() > 1) {
            throw new RuntimeException("selectOne方法查询出了多于1个结果");
        }
        return list.get(0);
	}
	
	 /**
	    * 此方法获取当前泛型类的真实类型
	    * @param id 主键id
	    * @return 返回当前泛型类的真实类型
	    * @author hey
	    * @version 1.00
	    */
	    @SuppressWarnings("all")
	    protected Notice createInstanceAndSetIdOfFirstGeneric(Long id) {
	        if (id == null) {
	        	//为了避免错误的将id设为null时把整张表都删除，在此检查
	            throw new RuntimeException("id不能为null");
	        }
	        try {
	            //通过反射创建泛型类对象并调用其setId方法设置id字段值
	            ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
	            Class clazz = (Class) type.getActualTypeArguments()[0];
	            Notice t = (Notice) clazz.newInstance();
	            clazz.getDeclaredMethod("setId", Long.class).invoke(t, id);
	            return t;
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	public boolean updateDisabled(Long userID, boolean b) {
		Notice notice= new Notice();
		notice.setId(userID);
		notice.setIsDisabled(b);
		return noticeMapper.updateDisabled(notice);
	}

	public Notice selectisDelete(Notice notice) {
		List<Notice> list = noticeMapper.selectisDelete(notice);
		if(list!=null && list.size()>1){
			throw new RuntimeException("selectOne方法查询出了多条数据");
		}
		if(list==null || list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public void updateDisDelete(Long id, boolean b) {
		Notice notice= new Notice();
		notice.setId(id);
		notice.setIsDeleted(b);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		notice.setCreateTime(sdf.format(new Date()));
		noticeMapper.updateDisDelete(notice);
	}

}
