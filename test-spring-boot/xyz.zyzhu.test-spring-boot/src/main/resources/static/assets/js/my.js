function handle(delta) {
    var s = delta + ": ";
   document.getElementById('showimgdiv').style.display='none';
}
function wheel(event){
    var delta = 0;
    if (!event) event = window.event;
    if (event.wheelDelta) {
        delta = event.wheelDelta/120; 
        if (window.opera) delta = -delta;
    } else if (event.detail) {
        delta = -event.detail/3;
    }
    if (delta)
        handle(delta);
}
	if (window.addEventListener)
		window.addEventListener('DOMMouseScroll', wheel, false);
	window.onmousewheel = document.onmousewheel = wheel;
	var t,p,w;
	var colors=new Array("FF0000","FFFF66","FF3399","00FFFF","FF9900","00FF00");
	function loadtext(i)
	{
		var innertext=document.getElementById("talk").innerTEXT;
		var innerhtml="<font id='fnts' color='#"+colors[i]+"' face='Tahoma' size='3'>"+innertext+"</font>"
		// For IE
		if (document.all)
		{
			talk.innerHTML= innerhtml;
			p=setTimeout("loadtext("+((++i)>=colors.length?0:i)+")",500);
		}
		
		else if (document.layers) 
		{
			document.talk.document.write(innerhtml);
			document.talk.document.close();
			p=setTimeout("loadtext("+((++i)>=colors.length?0:i)+")",500);
		}
			
		// For other
		else if (document.getElementById)
		{
			document.getElementById("talk").innerHTML = innerhtml;
			p=setTimeout("loadtext("+((++i)>=colors.length?0:i)+")",500);
			
		}
	}
	function loadweblogtext(i)
	{
		if(document.getElementById("fnts")!=null)document.getElementById("fnts").style.display='none';
		document.getElementById("wb").style.display='';
	}
	function mouseover(obj)
	{
	var li_type=obj.id;
	clearTimeout(t);
	clearTimeout(p);
	clearTimeout(w);
	document.getElementById("showimghref").href="javascript:void(0);";
	document.getElementById("showimgdiv").style.display='';
	document.getElementById("showimg").src="assets/images/"+obj.id+"show.jpg";
	document.getElementById("showimgdiv").style.bottom=window.innerHeight-document.getElementById("footer").offsetHeight;
	document.getElementById("showimgdiv").style.height='120px';
	document.getElementById("showimgdiv").style.left=(0.5*window.innerWidth-60)+'px';
	document.getElementById("showimgdiv").style.opacity=1.0;
	document.getElementById("showimgdiv").style.filter = "alpha(opacity=100)"
	if(li_type=='wechat')
	{
		document.getElementById("wb").style.display='none';
		document.getElementById("talk").innerTEXT="手机扫码关注";
		loadtext(0);
	}
	else if(li_type=='qq')
	{
		document.getElementById("wb").style.display='none';
		document.getElementById("talk").innerTEXT="点我进行交谈";
		document.getElementById("showimghref").href="http://wpa.qq.com/msgrd?v=3&uin=309332998&site=qq&menu=yes"
		loadtext(0);
	}
	else if(li_type=='weblog')
	{
		loadweblogtext(0);
	}
	else if(li_type='phone')
	{
		
		document.getElementById("wb").style.display='none';
		document.getElementById("talk").innerTEXT="崔先生:15011052173";
		document.getElementById("showimghref").href="tel:15011052173"
		loadtext(0);
	}
	
	}
	function mouseout(i)
	{
		if(i==0)
		{
			todisplay();
		}
		document.getElementById("showimgdiv").style.opacity=i/100;
		typeobj=i*100;
		document.getElementById("showimgdiv").style.filter = "alpha(opacity="+typeobj+"%)"
		if(i>0)
		{
			t=setTimeout("mouseout("+(--i)+")",20);
		}
	}
	function todisplay()
	{
		document.getElementById("showimgdiv").style.opacity=1.0;
		document.getElementById("showimgdiv").style.filter = "alpha(opacity=100)"
		document.getElementById("showimgdiv").style.display='none';
		document.getElementById("wb").style.display='none';
	}
	function toalpha(obj)
	{
		document.getElementById("showimgdiv").style.opacity=obj;
		typeobj=obj*100;
		document.getElementById("showimgdiv").style.filter = "alpha(opacity="+typeobj+")"
		if(obj==0)
		{
			todisplay();
		}
	}