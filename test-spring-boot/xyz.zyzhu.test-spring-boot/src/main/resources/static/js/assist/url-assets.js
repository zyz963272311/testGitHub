//本js为连接复制js
//获取url
function getUrl()
{
	return String(window.document.location.href);
}
//获取url上的参数列表
function getUrlParams(url)
{
	if(!url)
	{
		url = getUrl();
	}
	if(!url||url.indexOf('?')<0)
	{
		return {};
	}
	var p = url.indexOf('?');
	var paramStr = url.substring(p+1);
	var paramsArray = paramStr.split("&");
	var result = {};
	for(var i = 0;i<paramsArray.length;i++)
	{
		var params = paramsArray[i];
		var paramIdx  = params.indexOf("=");
		if(paramIdx>0&&paramIdx<params.length-2)
		{
			var paramName = params.substring(0,paramIdx);
			var paramValue = params.substring(paramIdx+1);
			result[paramName] = paramValue;
		}
	}
	return result;
}
//获取url上的一个参数名对应的参数值
//url可以不传,若不传则自动获取地址栏上的url
function getUrlParamByName(name,url)
{
	if(!name)
	{
		return null;
	}
	if(!url)
	{
		url = getUrl();
	}
	var params = getUrlParams(url);
	if(!params)
	{
		return null;
	}
	return params[name];
}
//参数路径添加参数
function addParam(url,param)
{
	if(!url||!param)
	{
		return url;
	}
	var p= url.indexOf('?');
	if(p>0)
	{
		url=url+"&"+param;
	}
	else
	{
		url=url+"?"+param;
	}
	return url;
}