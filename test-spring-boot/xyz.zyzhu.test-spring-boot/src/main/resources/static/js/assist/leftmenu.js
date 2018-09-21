function loadLeftMenu(params,selectId)
{
	if(params)
	{
		if(!selectId)
		{
			selectId = "#selectId";
		}
		var tempParams = {};
		tempParams = {
			nodeColumn:"node",
			nameColumn:"mname",
			urlColumn:"url",
			menuTable:"menu",
			strSplit:"."
		};
		ajaxurl = params.ajaxurl;
		if(!ajaxurl)
		{
			return null;
		}
		method = params.method;
		if(!method)
		{
			method = "POST";
		}
		var result;
		paramsStr = JSON.stringify(tempParams);
		if(paramsStr&&paramsStr.length>=2)
		{
			paramsStr = paramsStr.substring(2,paramsStr.length-2);
		}
		$.ajax({
			type:method,
			url:ajaxurl,
			async:false,
			data:tempParams,
			datatype:"json",
			success:function(data){
				result = data;
		    } 
		});
		return result;
	}
	else
	{
		return null;
	}
}