//微信的拓展js
$().ready(
	function loadLeftNenuByParams()
	{
		var ua = navigator.userAgent.toLowerCase();
		var isWeixin = ua.indexOf('micromessenger') != -1;
		if (isWeixin) 
		{
			//在微信打开的页面
		}
		else
		{
			//非微信打开的页面
		}
	}
);



