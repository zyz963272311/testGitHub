//根据table的选择器与主键列获取删除数据时候组装的数据    当idCol为*的情况下为获取所有的列
//tableElemSelecter table的选择器
//idCol  需要的列名
//isThrow 是否抛出异常
function getIdSelections(tableElemSelecter, idCol,isThrow) {
	if (tableElemSelecter === null || tableElemSelecter === undefined) {
		if (isThrow) {
			throw "选择器不可为空";
		}
		return null;
	}
	if (idCol === null || idCol === undefined) {
		if (isThrow) {
			throw "列名不可为空";
		}
		return null;
	}
	var tbElement = $(tableElemSelecter);
	if (tbElement === null || tbElement === undefined || tbElement.length == 0
			|| tbElement.length > 1) {
		if (isThrow) {
			throw "根据选择器找到多个对象";
		}
		return null;
	}
		var data = tbElement.bootstrapTable('getSelections');
	if(idCol!="*")
	{
		var idCols = idCol.split(',');
		return $.map(data, function(row) {
			var rowValue = {};
			for (var i = 0; i < idCols.length; i++) {
				var name = idCols[i];
				if (name != null && name != undefined) {
					rowValue[idCols[i]] = row[idCols[i]];
				}
			}
			return rowValue;
		});
	}
	else
	{
		return $.map(data, function(row) {
			var rowValue = {};
			for (var key in row) {
				var name = key;
				if (name != null && name != undefined) {
					rowValue[key] = row[key];
				}
			}
			return rowValue;
		});
	}
}
//界面查询条件初始化
//pageParams 格式为[{id:id选择器,name:名称,sqlname:sql名称,compareMethod:比较方式,prefix:表名前缀}]
//比较方式参考类 CompareMethodConstance
function initPageParams(pageParams)
{
	if(pageParams)
	{
		return;
	}
	for(var i = 0;i<pageParams.length;i++)
	{
		var pageParam = pageParams[i];
		if(pageParam)
		{
			var id = pageParam.id;
			var name = pageParam.name;
			var sqlname = pageParam.sqlname;
			var compareMethod = pageParam.compareMethod;
			var prefix = pageParam.prefix;
			if(id)
			{
				var elements = $(id);
				//当类型为redio的情况下，name可能会有多个
				if(elements === null||elements.length == 0 )
				{
					continue;
				}
				for(var j = 0;j<elements.length;j++)
				{
					var element = elements[0];
					element.data("name",name);
					element.data("sqlname",sqlname);
					element.data("compareMethod",compareMethod);
					element.data("prefix",prefix);
				}
			}
		}
	}
}
//获取所有的查询条件
//组装的结果为[{name:名称,sqlname:sql名称,compareMethod:比较方式,prefix:表名前缀,value:值}]
function getPageParams(pageParams)
{
	if(pageParams)
	{
		return null;
	}
	var resultParams = [];
	for(var i = 0;i<pageParams.length;i++)
	{
		var pageParam = pageParams[i];
		if(pageParam)
		{
			var id = pageParam.id;
			var name = pageParam.name;
			var sqlname = pageParam.sqlname;
			var compareMethod = pageParam.compareMethod;
			var prefix = pageParam.prefix;
			if(id)
			{
				var elements = $(id);
				//暂时不考虑radio
				if(elements === null||elements.length == 0 ||elements.length>1)
				{
					continue;
				}
				var element = elements[0];
				var resultParam = {};
				resultParam.name = name;
				resultParam.sqlname = sqlname;
				resultParam.compareMethod = compareMethod;
				resultParam.prefix = prefix;
				//目前仅仅考虑 text类型
				var value=  element.value;
				if(value)
				{
					element.value = value;
					resultParams[resultParams.length] = resultParam;
				}
			}
		}
	}
}