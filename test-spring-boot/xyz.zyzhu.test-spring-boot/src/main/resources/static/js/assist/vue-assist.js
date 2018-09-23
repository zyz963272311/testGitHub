//vue部分拓展函数
//判断方法  传递进来的参数为 arguments 为可变参数
function judgeMethod()
{
	if(arguments === null || arguments.length ==0)
	{
		return false;
	}
	if(arguments.length==1)
	{
		var args = arguments[0];
		var funcname = args[0];
		if(args.length == 1)
		{
			return eval(funcname+"()");
		}
		else
		{
			var params = [];
			for(var i = 1;i<args.length;i++)
			{
				params[i-1] = args[i];
			}
			var result = doCallBack(eval(funcname),params);
			return result;
		}
	}
}
//进行回调,类似于java反射的方式进行回调
function doCallBack(fn,args)
{
	return fn.apply(this,args);
}
//判断数组是否不为空
function arrayNotNull()
{ 
	var a = arguments[0];
	if(a === null)
	{
		return false;
	}
	return (Array.isArray(a) && a.length >0) || (Object.prototype.isPrototypeOf(a) && Object.keys(a).length > 0)
}
//判断数组是否为空
function arrayIsNull(a)
{
	return !arrayNotNull();
}