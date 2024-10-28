<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<% 
    Calendar calendar=Calendar.getInstance();
	calendar.add(calendar.MONTH, 1);
    int year,month,day;
    String date[]=new String[7];
    for(int j=0;j<7;j++){
    	calendar.add(calendar.MONTH, -1);
    	year=calendar.get(Calendar.YEAR); 
        month=calendar.get(Calendar.MONTH)+1; 
        day=calendar.get(Calendar.DATE);  
    	date[j]=year+"年"+(month)+"月";
    }
    String[] carp=new String[7];
    String[] parp=new String[7];
    for(int i=0;i<7;i++){
    	carp[i]=(String)request.getAttribute("C"+i);
    	parp[i]=(String)request.getAttribute("P"+i);
    }
 %> 
<html> 
<head>
	<title>test</title>
	<meta charset="utf-8">
	<style type="text/css">
		.big .1{
			float=left;
			}
		.big .2{
			float=right;
			}
	</style>
	<style type="text/css">  
	        table  
	        {  
	            border-collapse: collapse;  
	            margin: 0 auto;  
	            text-align: center;  
	        }  
	        table td, table th  
	        {  
	            border: 1px solid #cad9ea;  
	            color: #666;  
	            height: 30px;  
	        }  
	        table thead th  
	        {  
	            background-color: #CCE8EB;  
	            width: 100px;  
	        }  
	        table tr:nth-child(odd)  
	        {  
	            background: #fff;  
	        }  
	        table tr:nth-child(even)  
	        {  
	            background: #F5FAFA;  
	        }  
	    </style> 
		<script src="scripts/echarts/echarts.common.min.js"></script>
		<script>
			window.onload=function(){
				var sumc=<%=request.getAttribute("sumC")%>;
				var sump=<%=request.getAttribute("sumP")%>;
				var sum=sumc+sump;
				document.getElementById("car").innerHTML=(sumc/sum*100).toFixed(2)+"%";
				document.getElementById("parts").innerHTML=(sump/sum*100).toFixed(2)+"%";
				document.getElementById("sumc").innerHTML=sumc;
				document.getElementById("sump").innerHTML=sump;
			}
		</script>
</head>
<body>
	
	<div class="big">
		<div id="main1" class="1" style="width: 900px;height:400px; float:left"></div>
		<div id="main2" class="2" style="width: 450px;height:400px;float:left"></div>
		<div id="null" style="width:450px;height:150px; float:left" ></div>
		<div id="text1" style="width:450px;height:250px; float:left" >
			<table width="100%" class="table">
				<tr>
					<th width="34%">类别</th>
					<th width="34%">占比</th>
					<th width="32%">总收入</th>
				</tr>
				<tr>
					<td>汽车销售</td>
					<td id="car"></td>
					<td id="sumc"></td>
				</tr>
				<tr>
					<td>额外服务</td>
					<td id="parts"></td>
					<td id="sump"></td>
				</tr>
			</table>
		</div>
    </div>  
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
		 echarts.init(document.getElementById('main2')).setOption({
        	title: {text: '总财务收入'},
            series: {
                type: 'pie',
                data: [
                    {name: '车辆销售', value: <%=request.getAttribute("sumC")%>},
                    {name: '额外服务', value: <%=request.getAttribute("sumP")%>}
                ]
            }
        });
		 echarts.init(document.getElementById('main1')).setOption({
		    title: {
		        text: '按月财务收入/￥'
		    },
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['车辆销售','额外服务']
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    toolbox: {
		        feature: {
		            saveAsImage: {}
		        }
		    },
		    xAxis: {
		        type: 'category',
		        boundaryGap: false,
		        data: ['<%=date[6]%>','<%=date[5]%>','<%=date[4]%>','<%=date[3]%>','<%=date[2]%>','<%=date[1]%>','<%=date[0]%>']
		    },
		    yAxis: {
		        type: 'value'
		    },
		    series: [
		        {
		            name:'车辆销售',
		            type:'line',
		            stack: '总量',
		            data:[<%=carp[6]%>, <%=carp[5]%>, <%=carp[4]%>, <%=carp[3]%>, <%=carp[2]%>, <%=carp[1]%>, <%=carp[0]%>]
		        },
		        {
		            name:'额外服务',
		            type:'line',
		            stack: '总量',
		            data:[<%=parp[6]%>,<%=parp[5]%>, <%=parp[4]%>, <%=parp[3]%>, <%=parp[2]%>, <%=parp[1]%>, <%=parp[0]%>]
		        }
		    ]
		});
    </script>
</body>
</html>