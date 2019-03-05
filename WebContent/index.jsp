<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生列表</title>
<script type="text/javascript">
	
	$(function(){
		$("#nav a").click(function(){
			var title = $(this).text();
			var url = $(this).attr("url");
			//$.messager.alert("提示",$(this).text(),"info");
			if($('#tt').tabs('exists',title)){//选项页存在
				$('#tt').tabs('select',title);//选中
			}else{
				$('#tt').tabs('add',{    
				    title:title,    
				    content:"<iframe src='"+url+"' style='width: 100%;height: 100%' frameborder='0'></iframe>",
				    closable:true 
				});
			}
		});
	});
	
</script>

</head>
<body>
	<div id="cc" class="easyui-layout" fit="true">   
	    <div data-options="region:'north',split:false,border:false" style="height:100px;">
	    	
	    </div>   
	    <div data-options="region:'south',border:false,split:false" style="height:100px;">
	    	
	    </div>   
	    <div data-options="region:'west',title:'导航菜单',iconCls:'icon-save',split:false" style="width:150px;">
	    	<div id="nav" class="easyui-accordion" fit="true">   
			    <div title="系统管理" data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">   
		          	<ul class="easyui-tree" fit="true">
		          	<li><a href="#" url="user.jsp">用户管理</a></li>
		          	<li><a href="#" url="role.jsp">角色管理</a></li>
		          	<li><a href="#" url="menu.jsp">菜单管理</a></li>
		          	<li><a href="#" url="userRole.jsp">用户角色管理</a></li>
		          	<li><a href="#" url="roleMenu.jsp">角色菜单管理</a></li>
		          	<li><a href="#" url="log.jsp">用户日志管理</a></li>
		          	</ul>
			    </div>
			     
			    <div title="人员档案管理" data-options="iconCls:'icon-save',selected:true">
			          <ul class="easyui-tree" fit="true">
			          	<li><a href="#" url="department.jsp">部门管理</a></li>
			          	<li><a href="#" url="employee.jsp">员工管理</a></li>
			          	<li><a href="#" url="archivesType.jsp">档案类型管理</a></li>
			          	<li><a href="#" url="archives.jsp">档案管理</a></li>
			          	<li><a href="#" url="resume.jsp">履历管理</a></li>
			          	<li><a href="#" url="contract.jsp">合同管理</a></li>
			          	<li><a href="#" url="rewards.jsp">奖惩管理</a></li>
			          	<li><a href="#" url="student.jsp">学生信息管理</a></li>
			          </ul>
			    </div>
		    
			    <div title="人事调配管理" data-options="iconCls:'icon-save'">
			        <ul class="easyui-tree" fit="true">
		          	<li><a href="#" url="deploy.jsp">人事调动管理</a></li>
		          	<li><a href="#" url="engage.jsp">聘任管理</a></li>
		          	</ul>
			    </div>
			    
			    <div title="教育培训管理" data-options="iconCls:'icon-save'">   
			        <ul class="easyui-tree" fit="true">
		          	<li><a href="#" url="eduType.jsp">培训类别管理</a></li>
		          	<li><a href="#" url="edu.jsp">培训管理</a></li>
		          	<li><a href="#" url="eduScore.jsp">培训成绩管理</a></li>
		          	<li><a href="#" url="certificate.jsp">员工证书管理</a></li>
		          	</ul>
			    </div>
			</div>
	    </div>
	    <div data-options="region:'center'">
	    	<div id="tt" class="easyui-tabs" fit="true" border="false">   
			    <div title="欢迎页" style="text-align:center;font-size: 20px;">   
			         	欢迎使用XXX系统后台管理界面！ 
			    </div>
			</div>
	    </div>   
	</div>
</body>
</html>