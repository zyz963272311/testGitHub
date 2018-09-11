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