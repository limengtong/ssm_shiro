package com.sys.mapper;

import java.util.List;
import java.util.Map;

import com.sys.pojo.Notice;

/**
 * 公告管理mapper
 * @author Administrator
 *
 */
public interface NoticeMapper {
	
	// 查询总数
	Long selectCount();
	
	// 分页查询
	List<Notice> selectByPage(Map<String, Long> map);
	
	/**
	* 根据id删除数据
	* @param id 数据的id值
	* @return 返回删除的数据条数
	* @author hey
	* @version 1.00
	*/
	int delete(Long id);
	
	/**
	* 以非空字段值作为查询条件进行查询
	* @param Notice
	* @return 查询到的结果集，以集合形式返回
	* @author hey
	* @version 1.00
	*/
	List<Notice> select(Notice notice);
	
	/**
	 * 添加公告
	 * @param pojo
	 * @return
	 */
	int insert(Notice pojo);
	
    /**
    * @Description: 禁用/启用
    * @param adminUser
    * @return
    * @throws  
    * @author lao
    * @Date 2018年1月9日上午10:08:36
    * @version 1.00
     */
	Boolean updateDisabled(Notice notice);
	
    /**
    * @Description: 查询是否是删除状态
    * @param adminUser
    * @return
    * @throws  
    * @author lao
    * @Date 2018年1月9日上午10:08:36
    * @version 1.00
     */
	List<Notice> selectisDelete(Notice notice);
	
    /**
    * @Description: 删除/恢复
    * @param adminUser
    * @return
    * @throws  
    * @author lao
    * @Date 2018年1月9日上午10:08:36
    * @version 1.00
     * @return 
     */	
	void updateDisDelete(Notice notice);


}
