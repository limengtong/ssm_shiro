	/**分页*/
	function nextPage(currPage,pageSize){	
		window.location="list.do?currPage="+currPage+"&pageSize="+pageSize+"";
	}
	/**每页显示*/
	function showPageSize(){
		var pageSize = document.getElementById("pageSize").value;
		if(pageSize.length>0){
			nextPage(1,pageSize);
		}		
	}
         		
	/**跳转到具体页*/
	function jumpPage(totalPage,pageSize){
		var page = document.getElementById("jumpPage").value;
		var reg = /^[0-9]*$/;
		if (0!=page&&reg.test(page)&&totalPage>=page) {
			nextPage(page,pageSize);
		}else{				
			layer.msg("请输入正确页数!");
		}
	}
	/**添加公告提交*/
	function noticeAdd(){
		var account = jQuery("#account").val();
		if(account==null||account==""){
			layer.msg('公告内容不能为空！');
			return false;
		}
		
		var data = {account:account};
		var load = layer.load();
		jQuery.ajax({
			url:"checkNotice.do",
			data:data,
			type:"post",
			dataType:"json",
			success:function(result){
				layer.close(load);
				if(result && result.status == 200 && result.message == true){
					noticeAddSave(account)
				}else{
					layer.msg('此公告已存在！');
				    return false;
				}
			},
			error:function(e){
			    layer.msg('输入公告无法识别！');
			    return false;
			}
		});
	}
	
	/**添加公告提交保存*/
	function noticeAddSave(account){
		var data = {account:account};
		var load = layer.load();
		jQuery.ajax({
			url:"noticeAdd.do",
			data:data,
			type:"post",
			dataType:"json",
			beforeSend:function(){
				layer.msg('正在处理...');
			},
			success:function(result){
				layer.close(load);
				if(result && result.status != 200){
					layer.msg(result.message,function(){});
				    return;
				}else{
				    layer.msg(result.message);
				    setTimeout(function(){
			    		window.location.href= result.back_url || "list.do";
		    		},1000)
				}
			},
			error:function(e){
			    layer.msg('添加提交失败！');
			}
		});
	}
	
	/**删除公告*/
	function noticeDel(id){
		$(".hintl").css({"display":"block"});
		$(".box").css({"display":"block"});
	    
		$(".hintl-in3").click(function(event) {
			$(".hintl").css({"display":"none"});
			$(".box").css({"display":"none"});
			window.location="delete.do?id="+id+"";
		});
		
		$(".hintl-in4").click(function() {
			$(".hintl").css({"display":"none"});
			$(".box").css({"display":"none"});
		});
	}
